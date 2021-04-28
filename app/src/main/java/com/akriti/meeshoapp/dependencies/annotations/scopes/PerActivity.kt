package com.akriti.base.injection.annotations.scopes

import javax.inject.Scope

/**
 * Activity scope for Dagger.
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PerActivity