package com.akriti.gitprapp.dependencies

import android.content.Context
import com.akriti.gitprapp.view.injection.activity.MainActivityBuilder
import com.akriti.gitprapp.dependencies.annotations.qualifiers.ForApplication
import com.akriti.gitprapp.dependencies.annotations.scopes.PerApplication
import com.akriti.gitprapp.GitPRApp
import com.akriti.gitprapp.dependencies.annotations.qualifiers.ForComputationThread
import com.akriti.gitprapp.dependencies.annotations.qualifiers.ForIoThread
import com.akriti.gitprapp.dependencies.annotations.qualifiers.ForMainThread
import com.akriti.gitprapp.network.injection.NetworkModule
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module(includes = [
    MainActivityBuilder::class,
    NetworkModule::class
])
class GitPRAppModule {

    @Provides
    @ForApplication
    fun provideApplication(application: GitPRApp): Context = application

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