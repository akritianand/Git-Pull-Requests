package com.akriti.gitprapp.dependencies

import android.app.Application
import com.akriti.gitprapp.dependencies.annotations.scopes.PerApplication
import com.akriti.gitprapp.GitPRApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@PerApplication
@Component(modules = [
    AndroidSupportInjectionModule::class,
    GitPRAppModule::class
])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: GitPRApp)
}