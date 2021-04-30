package com.akriti.meeshoapp.view.injection.fragment

import com.akriti.base.injection.annotations.scopes.PerFragment
import com.akriti.meeshoapp.view.PRListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class PRListFragmentBuilder {

    @PerFragment
    @ContributesAndroidInjector(modules = [PRListFragmentModule::class])
    abstract fun bindPRListFragment(): PRListFragment
}