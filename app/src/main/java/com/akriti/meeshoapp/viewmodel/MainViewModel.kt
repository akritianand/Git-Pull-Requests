package com.akriti.meeshoapp.viewmodel

import androidx.lifecycle.ViewModel
import com.akriti.meeshoapp.domain.GetPullRequestsUseCase
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getPullRequestsUseCase: GetPullRequestsUseCase
) : ViewModel() {


    var searchText: String = ""

    fun fetchPullRequests(input: String) {
        getPullRequestsUseCase.execute(input)
    }

    fun onFetchPullRequestsSuccess(pullRequests: List<String>) {
    }
}