package com.akriti.meeshoapp.view.injection.activity

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.akriti.base.injection.annotations.qualifiers.ForActivity
import com.akriti.base.injection.annotations.scopes.PerActivity
import com.akriti.base.injection.annotations.scopes.PerFragment
import com.akriti.meeshoapp.view.MainActivity
import com.akriti.meeshoapp.viewmodel.MainViewModel
import com.akriti.meeshoapp.viewmodel.MainViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    @PerActivity
    @ForActivity
    internal fun provideMainActivityContext(activity: MainActivity): Context = activity

    @Provides
    @PerActivity
    fun provideMainViewModel(activity: MainActivity, factory: MainViewModelFactory): MainViewModel =
        ViewModelProvider(activity, factory).get()
}