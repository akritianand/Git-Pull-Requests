package com.akriti.gitprapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akriti.gitprapp.domain.GetPullRequestsUseCaseImpl
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(
    private val getPullRequestsUseCase: GetPullRequestsUseCaseImpl,
    private val fetchPullRequestsResultLiveData: MutableLiveData<MainViewModel.LiveDataState>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        MainViewModel(getPullRequestsUseCase, fetchPullRequestsResultLiveData) as T
}