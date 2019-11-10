package com.urban.noise.library.core.di.scopes

import javax.inject.Scope

/**
 * Scope for the entire app runtime.
 */
@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class AppScope
