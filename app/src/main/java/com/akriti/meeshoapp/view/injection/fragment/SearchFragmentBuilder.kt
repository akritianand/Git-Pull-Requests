package com.akriti.meeshoapp.view.injection.fragment

import com.akriti.base.injection.annotations.scopes.PerFragment
import com.akriti.meeshoapp.view.SearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SearchFragmentBuilder {

    @PerFragment
    @ContributesAndroidInjector(modules = [SearchFragmentModule::class])
    abstract fun bindSearchFragment(): SearchFragment
}