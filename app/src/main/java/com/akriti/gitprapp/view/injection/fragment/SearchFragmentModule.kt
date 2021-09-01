package com.akriti.gitprapp.view.injection.fragment

import android.content.Context
import com.akriti.gitprapp.dependencies.annotations.qualifiers.ForFragment
import com.akriti.gitprapp.dependencies.annotations.scopes.PerFragment
import com.akriti.gitprapp.view.SearchFragment
import dagger.Module
import dagger.Provides

@Module
class SearchFragmentModule {

    @Provides
    @PerFragment
    @ForFragment
    fun provideSearchFragmentContext(fragment: SearchFragment): Context = fragment.requireContext()
}