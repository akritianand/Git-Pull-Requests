package com.akriti.base.injection.annotations.qualifiers

import javax.inject.Qualifier

/**
 * Qualifier used to mark instances managed by Dagger related to an activity.
 */
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ForActivity