package com.akriti.gitprapp.view.injection.activity

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.akriti.gitprapp.dependencies.annotations.qualifiers.ForActivity
import com.akriti.gitprapp.dependencies.annotations.scopes.PerActivity
import com.akriti.gitprapp.dependencies.FragmentBindings
import com.akriti.gitprapp.domain.injection.GetPullRequestsUseCaseModule
import com.akriti.gitprapp.view.MainActivity
import com.akriti.gitprapp.viewmodel.MainViewModel
import com.akriti.gitprapp.viewmodel.MainViewModelFactory
import dagger.Module
import dagger.Provides

@Module(includes = [
    GetPullRequestsUseCaseModule::class,
    FragmentBindings::class,
])
class MainActivityModule {

    @Provides
    @PerActivity
    @ForActivity
    internal fun provideMainActivityContext(activity: MainActivity): Context = activity

    @Provides
    @PerActivity
    fun provideMainViewModel(activity: MainActivity, factory: MainViewModelFactory): MainViewModel =
        ViewModelProvider(activity, factory).get()

    @Provides
    @PerActivity
    fun provideMutableLiveData() = MutableLiveData<MainViewModel.LiveDataState>()
}