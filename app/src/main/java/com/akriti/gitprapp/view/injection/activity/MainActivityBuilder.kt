package com.akriti.gitprapp.view.injection.activity

import com.akriti.gitprapp.dependencies.annotations.scopes.PerActivity
import com.akriti.gitprapp.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityBuilder {

    @PerActivity
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity(): MainActivity
}