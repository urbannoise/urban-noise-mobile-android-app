package com.urban.noise.feature.splashscreen.ui.di

import com.urban.noise.feature.splashscreen.ui.SplashscreenFragment
import com.urban.noise.library.core.di.CoreComponent
import com.urban.noise.library.core.di.scopes.FeatureScope
import dagger.Component

@FeatureScope
@Component(
    modules = [SplashscreenModule::class],
    dependencies = [CoreComponent::class]
)
interface SplashscreenComponent {

    fun inject(splashscreenFragment: SplashscreenFragment)
}
