package com.akriti.meeshoapp.dependencies

import android.content.Context
import com.akriti.meeshoapp.view.injection.activity.MainActivityBindings
import com.akriti.base.injection.annotations.qualifiers.ForApplication
import com.akriti.meeshoapp.MeeshoApp
import dagger.Module
import dagger.Provides

@Module(includes = [
    MainActivityBindings::class,
    FragmentBindings::class
])
class MeeshoAppModule {

    @Provides
    @ForApplication
    fun provideApplication(application: MeeshoApp): Context = application

}