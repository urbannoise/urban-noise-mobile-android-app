package com.urban.noise.library.core.di

import android.content.Context
import com.urban.noise.library.core.di.modules.ContextModule
import dagger.Component
import javax.inject.Singleton

/**
 * Core component that all module's components depend on.
 */
@Singleton
@Component(modules = [ContextModule::class])
interface CoreComponent {

    fun context(): Context

}
