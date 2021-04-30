package com.akriti.meeshoapp.view.injection.activity

import com.akriti.base.injection.annotations.scopes.PerActivity
import com.akriti.meeshoapp.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityBuilder {

    @PerActivity
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity(): MainActivity
}