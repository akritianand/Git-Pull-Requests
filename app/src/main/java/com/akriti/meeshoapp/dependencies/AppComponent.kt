package com.akriti.meeshoapp.dependencies

import android.app.Application
import com.akriti.base.injection.annotations.scopes.PerApplication
import com.akriti.meeshoapp.MeeshoApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@PerApplication
@Component(modules = [
    AndroidSupportInjectionModule::class,
    MeeshoAppModule::class
])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: MeeshoApp)
}