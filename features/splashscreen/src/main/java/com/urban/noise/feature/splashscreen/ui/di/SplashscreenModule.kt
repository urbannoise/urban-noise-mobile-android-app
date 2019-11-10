package com.urban.noise.feature.splashscreen.ui.di

import com.urban.noise.feature.splashscreen.ui.SplashscreenFragment
import com.urban.noise.feature.splashscreen.ui.SplashscreenViewModel
import com.urban.noise.library.core.di.scopes.FeatureScope
import com.urban.noise.library.core.extensions.viewModel

import dagger.Module
import dagger.Provides

@Module
class SplashscreenModule(private val fragment: SplashscreenFragment) {

    @Provides
    @FeatureScope
    fun providesSplashscreenViewModel(): SplashscreenViewModel {
        return fragment.viewModel {
            SplashscreenViewModel()
        }
    }
}
