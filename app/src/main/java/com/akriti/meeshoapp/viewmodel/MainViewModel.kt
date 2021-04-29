package com.akriti.meeshoapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akriti.meeshoapp.domain.GetPullRequestsUseCase
import com.akriti.meeshoapp.domain.GetPullRequestsUseCaseImpl
import com.akriti.meeshoapp.model.PullRequestItem
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getPullRequestsUseCase: GetPullRequestsUseCaseImpl,
    val fetchPullRequestsResultLiveData: MutableLiveData<LiveDataState>
) : ViewModel(), GetPullRequestsUseCase.Callback {
    
    init {
        getPullRequestsUseCase.setCallback(this)
    }

    companion object {
        private const val ALL = "all"
        private const val OPEN = "open"
        private const val CLOSED = "closed"
    }

    sealed class LiveDataState {
        data class Success(val pullRequestsItem: List<PullRequestItem>): LiveDataState()
        object Error: LiveDataState()
    }

    fun fetchPullRequests(searchText: String) {
        if (searchText.isNotBlank()) {
            val input = searchText.split("/")
            if (input.size == 2) {
                getPullRequestsUseCase.execute(input[0], input[1], OPEN)
            }
        }
    }

    override fun onFetchPullRequestsSuccess(pullRequestsItem: List<PullRequestItem>) {
        fetchPullRequestsResultLiveData.value = LiveDataState.Success(pullRequestsItem)
    }

    override fun onFetchPullRequestsError(e: Throwable) {
        fetchPullRequestsResultLiveData.value = LiveDataState.Error
    }
}