package com.akriti.meeshoapp.view.injection.fragment

import android.content.Context
import android.view.LayoutInflater
import com.akriti.base.injection.annotations.qualifiers.ForFragment
import com.akriti.base.injection.annotations.scopes.PerFragment
import com.akriti.meeshoapp.view.SearchFragment
import dagger.Module
import dagger.Provides

@Module
class SearchFragmentModule {

    @Provides
    @PerFragment
    @ForFragment
    internal fun provideSearchFragmentContext(fragment: SearchFragment): Context = fragment.requireContext()

    @Provides
    @PerFragment
    internal fun provideLayoutInflater(@ForFragment context: Context): LayoutInflater =
        LayoutInflater.from(context)
}