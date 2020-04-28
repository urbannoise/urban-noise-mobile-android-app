package plugins

import utils.getLocalProperty
import org.sonarqube.gradle.SonarQubeExtension
import org.sonarqube.gradle.SonarQubePlugin

apply<SonarQubePlugin>()

configure<SonarQubeExtension> {
    properties {
        property("sonar.projectKey", getLocalProperty("sonar.projectKey", project))
        property("sonar.organization", getLocalProperty("sonar.organization", project))
        property("sonar.host.url", getLocalProperty("sonar.host.url", project))
        property("sonar.login", getLocalProperty("sonar.login", project))
        property(
            "sonar.androidLint.reportPaths",
            "build/reports/lint-results-devDebug.xml"
        )
        property(
            "sonar.kotlin.detekt.reportPaths",
            "build/reports/detekt/report.xml"
        )
        property(
            "sonar.coverage.jacoco.xmlReportPaths",
            "build/reports/jacoco/devDebug/jacoco.xml"
        )
    }
}
