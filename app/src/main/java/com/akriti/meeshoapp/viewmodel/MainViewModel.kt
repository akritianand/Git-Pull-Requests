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
        private const val PER_PAGE = 20
    }

    private var page = 0
    private var owner = ""
    private var repo = ""

    val items = mutableListOf<PullRequestItem>()

    sealed class LiveDataState {
        object Loading: LiveDataState()
        object Success: LiveDataState()
        object Error: LiveDataState()
    }

    fun fetchPullRequests() {
        if (owner.isNotBlank() && repo.isNotBlank()) {
            fetchPullRequestsResultLiveData.value = LiveDataState.Loading
            page++
            getPullRequestsUseCase.execute(owner, repo, OPEN, PER_PAGE, page)
        }
    }

    override fun onFetchPullRequestsSuccess(pullRequestsItem: List<PullRequestItem>) {
        items.addAll(pullRequestsItem)
        fetchPullRequestsResultLiveData.value = LiveDataState.Success
    }

    override fun onFetchPullRequestsError(e: Throwable) {
        fetchPullRequestsResultLiveData.value = LiveDataState.Error
    }

    fun setInput(input: String) {
        if (input.isNotBlank()) {
            val tokens = input.split("/")
            if (tokens.size == 2) {
                owner = tokens[0]
                repo = tokens[1]
            }
        }
    }

    fun clearInput() {
        owner = ""
        repo = ""
        items.clear()
        page = 0
    }

    fun hasMoreData() = items.size % PER_PAGE == 0
}