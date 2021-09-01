package com.akriti.gitprapp.view.injection.fragment

import com.akriti.gitprapp.dependencies.annotations.scopes.PerFragment
import com.akriti.gitprapp.view.PRListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class PRListFragmentBuilder {

    @PerFragment
    @ContributesAndroidInjector(modules = [PRListFragmentModule::class])
    abstract fun bindPRListFragment(): PRListFragment
}