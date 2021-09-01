package com.akriti.gitprapp.dependencies.annotations.qualifiers

import javax.inject.Qualifier

/**
 * Qualifier used to mark instances managed by Dagger related to the application.
 */
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ForApplication