/*
 * Copyright 2019 urbannoise.org
 *
 * Licensed under the Attribution-NonCommercial-ShareAlike 4.0
 * International (CC BY-NC-SA 4.0) you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import dependencies.Dependencies
import dependencies.DebugDependencies
import dependencies.AnnotationProcessorsDependencies
import extensions.addTestsDependencies
import utils.getLocalProperty

plugins {
    id(BuildPlugins.ANDROID_APPLICATION)
    id(BuildPlugins.KOTLIN_ANDROID)
    id(BuildPlugins.KOTLIN_ANDROID_EXTENSIONS)
    id(BuildPlugins.KOTLIN_KAPT)
    id(BuildPlugins.KOTLIN_ALLOPEN)
    id(BuildPlugins.NAVIGATION_SAFE_ARGS)
    id(BuildPlugins.JACOCO)
    id(BuildPlugins.GOOGLE_SERVICES)
    id(BuildPlugins.FIREBASE_CRASHLYTICS)
    id(BuildPlugins.FIREBASE_PERFORMANCE)
    id(BuildPlugins.PLAY_PUBLISHER)
}

allOpen {
    // allows mocking for classes w/o directly opening them for release builds
    annotation("org.urban.noise.base.android.annotations.OpenClass")
}

android {
    compileSdkVersion(BuildAndroidConfig.COMPILE_SDK_VERSION)
    defaultConfig {
        applicationId = BuildAndroidConfig.APPLICATION_ID
        minSdkVersion(BuildAndroidConfig.MIN_SDK_VERSION)
        targetSdkVersion(BuildAndroidConfig.TARGET_SDK_VERSION)
        buildToolsVersion(BuildAndroidConfig.BUILD_TOOLS_VERSION)

        versionCode = BuildAndroidConfig.VERSION_CODE
        versionName = BuildAndroidConfig.VERSION_NAME

        vectorDrawables.useSupportLibrary = BuildAndroidConfig.SUPPORT_LIBRARY_VECTOR_DRAWABLES
        testInstrumentationRunner = BuildAndroidConfig.TEST_INSTRUMENTATION_RUNNER
        testInstrumentationRunnerArguments =
            BuildAndroidConfig.TEST_INSTRUMENTATION_RUNNER_ARGUMENTS
    }

    signingConfigs {
        create(BuildType.RELEASE) {
            storeFile = file(getLocalProperty("sign.store.file", project))
            storePassword = getLocalProperty("sign.store.password", project)
            keyAlias = getLocalProperty("sign.key.alias", project)
            keyPassword = getLocalProperty("sign.key.password", project)
        }
    }

    buildTypes {
        getByName(BuildType.RELEASE) {
            proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")

            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
            isTestCoverageEnabled = BuildTypeRelease.isTestCoverageEnabled
            signingConfig = signingConfigs.getByName(BuildType.RELEASE)
        }

        getByName(BuildType.DEBUG) {
            applicationIdSuffix = BuildTypeDebug.applicationIdSuffix
            versionNameSuffix = BuildTypeDebug.versionNameSuffix
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
            isTestCoverageEnabled = BuildTypeDebug.isTestCoverageEnabled
        }
    }

    flavorDimensions(BuildProductDimensions.ENVIRONMENT)
    productFlavors {
        ProductFlavorDevelop.appCreate(this)
        ProductFlavorQA.appCreate(this)
        ProductFlavorProduction.appCreate(this)
    }

    dynamicFeatures = mutableSetOf(
        BuildModules.Features.SPLASHSCREEN,
        BuildModules.Features.TUTORIAL,
        BuildModules.Features.DASHBOARD
    )

    dataBinding {
        isEnabled = true
    }

    androidExtensions {
        isExperimental = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    lintOptions {
        lintConfig = rootProject.file(".lint/config.xml")
        isCheckAllWarnings = true
        isWarningsAsErrors = true
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
        unitTests.isReturnDefaultValues = true
    }

    sourceSets {
        getByName("main") {
            java.srcDir("src/main/kotlin")
        }
        getByName("test") {
            java.srcDir("src/test/kotlin")
        }
        getByName("androidTest") {
            java.srcDir("src/androidTest/kotlin")
        }
    }
}

play {
    serviceAccountCredentials = file(getLocalProperty("play.publisher.service.file", project))
    track = getLocalProperty("play.publisher.track", project)
    defaultToAppBundles = true
    resolutionStrategy = "auto"
    outputProcessor {
        versionNameOverride = "$versionNameOverride.$versionCode"
        val versionNameFile = "version.txt"
        rootProject.file(versionNameFile).writeText(versionNameOverride)
    }
}

junitJacoco {
    includeNoLocationClasses = true
}

dependencies {
    implementation(project(BuildModules.BASE_ANDROID))

    api(Dependencies.KOTLIN)
    api(Dependencies.APPCOMPAT)
    api(Dependencies.MATERIAL)
    api(Dependencies.COROUTINES)
    api(Dependencies.COROUTINES_ANDROID)
    api(Dependencies.CONSTRAINT_LAYOUT)
    api(Dependencies.LIFECYCLE_EXTENSIONS)
    api(Dependencies.LIFECYCLE_VIEWMODEL)
    api(Dependencies.CORE_KTX)
    api(Dependencies.FRAGMENT_KTX)
    api(Dependencies.NAVIGATION_DYNAMIC_FRAGMENT)
    api(Dependencies.TIMBER)
    api(Dependencies.DAGGER)

    implementation(Dependencies.LOGGING)
    implementation(Dependencies.PLAY_CORE)
    implementation(Dependencies.FIREBASE_ANALYTICS)
    implementation(Dependencies.FIREBASE_CRASHLYTICS)
    implementation(Dependencies.FIREBASE_PERFORMANCE)
    implementation(Dependencies.GMS_MAPS)

    kapt(AnnotationProcessorsDependencies.DAGGER)
    kapt(AnnotationProcessorsDependencies.DATABINDING)

    debugImplementation(DebugDependencies.LEAKCANARY)

    addTestsDependencies()
}
