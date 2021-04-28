package com.akriti.meeshoapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akriti.meeshoapp.domain.GetPullRequestsUseCase
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(
    private val getPullRequestsUseCase: GetPullRequestsUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        MainViewModel(getPullRequestsUseCase) as T
}