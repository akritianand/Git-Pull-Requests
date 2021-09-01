package com.akriti.gitprapp.view.injection.fragment

import com.akriti.gitprapp.dependencies.annotations.scopes.PerFragment
import com.akriti.gitprapp.view.SearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SearchFragmentBuilder {

    @PerFragment
    @ContributesAndroidInjector(modules = [SearchFragmentModule::class])
    abstract fun bindSearchFragment(): SearchFragment
}