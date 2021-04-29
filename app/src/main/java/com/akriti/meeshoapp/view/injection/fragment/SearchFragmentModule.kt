package com.akriti.meeshoapp.view.injection.fragment

import android.content.Context
import android.view.LayoutInflater
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.akriti.base.injection.annotations.qualifiers.ForFragment
import com.akriti.base.injection.annotations.scopes.PerActivity
import com.akriti.base.injection.annotations.scopes.PerFragment
import com.akriti.meeshoapp.domain.injection.GetPullRequestsUseCaseModule
import com.akriti.meeshoapp.view.SearchFragment
import com.akriti.meeshoapp.viewmodel.MainViewModel
import dagger.Module
import dagger.Provides

@Module(includes = [
    GetPullRequestsUseCaseModule::class
])
class SearchFragmentModule {

    @Provides
    @PerFragment
    @ForFragment
    fun provideSearchFragmentContext(fragment: SearchFragment): Context = fragment.requireContext()

    @Provides
    @PerFragment
    fun provideMutableLiveData(): MutableLiveData<MainViewModel.LiveDataState> = MutableLiveData()
}