package com.akriti.meeshoapp.dependencies

import android.content.Context
import com.akriti.meeshoapp.view.injection.activity.MainActivityBuilder
import com.akriti.base.injection.annotations.qualifiers.ForApplication
import com.akriti.base.injection.annotations.scopes.PerApplication
import com.akriti.meeshoapp.MeeshoApp
import com.akriti.meeshoapp.dependencies.annotations.qualifiers.ForComputationThread
import com.akriti.meeshoapp.dependencies.annotations.qualifiers.ForIoThread
import com.akriti.meeshoapp.dependencies.annotations.qualifiers.ForMainThread
import com.akriti.meeshoapp.network.injection.NetworkModule
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module(includes = [
    MainActivityBuilder::class,
    NetworkModule::class
])
class MeeshoAppModule {

    @Provides
    @ForApplication
    fun provideApplication(application: MeeshoApp): Context = application

    @Provides
    @PerApplication
    @ForIoThread
    internal fun provideIoScheduler(): Scheduler = Schedulers.io()

    @Provides
    @PerApplication
    @ForComputationThread
    internal fun provideComputationScheduler(): Scheduler = Schedulers.computation()

    @Provides
    @PerApplication
    @ForMainThread
    internal fun provideMainScheduler(): Scheduler = AndroidSchedulers.mainThread()

}