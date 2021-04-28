package com.akriti.meeshoapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akriti.meeshoapp.domain.GetPullRequestsUseCase
import com.akriti.meeshoapp.model.PullRequest
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getPullRequestsUseCase: GetPullRequestsUseCase,
    val fetchPullRequestsResultLiveData: MutableLiveData<LiveDataState>
) : ViewModel() {

    sealed class LiveDataState {
        data class Success(val pullRequests: List<PullRequest>): LiveDataState()
        object Error: LiveDataState()
    }

    var searchText: String = ""

    fun fetchPullRequests(input: String) {
        getPullRequestsUseCase.execute(input)
    }

    fun onFetchPullRequestsSuccess(content: List<PullRequest>) {
        fetchPullRequestsResultLiveData.value = LiveDataState.Success(content)
    }
}