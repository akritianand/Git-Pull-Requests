package com.akriti.gitprapp.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akriti.gitprapp.domain.GetPullRequestsUseCase
import com.akriti.gitprapp.domain.GetPullRequestsUseCaseImpl
import com.akriti.gitprapp.model.DisplayableItem
import com.akriti.gitprapp.model.PullRequestItem
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getPullRequestsUseCase: GetPullRequestsUseCaseImpl,
    val stateLiveData: MutableLiveData<LiveDataState>
) : ViewModel(), GetPullRequestsUseCase.Callback {
    
    init {
        getPullRequestsUseCase.setCallback(this)
    }

    companion object {
        private const val OPEN = "open"
        private const val PER_PAGE = 20
    }

    @VisibleForTesting
    var page = 1
    @VisibleForTesting
    var owner = ""
    @VisibleForTesting
    var repo = ""

    var items = emptyList<DisplayableItem>()

    sealed class LiveDataState {
        object Null: LiveDataState()
        object Loading: LiveDataState()
        object Success: LiveDataState()
        object Empty: LiveDataState()
        object Error: LiveDataState()
        object InvalidInput: LiveDataState()
    }

    fun fetchPullRequests(isFirstSearch: Boolean = false) {
        if (owner.isNotBlank() && repo.isNotBlank()) {
            stateLiveData.value = LiveDataState.Loading
            if (isFirstSearch)
                page = 1
            else
                page++
            getPullRequestsUseCase.execute(owner, repo, OPEN, PER_PAGE, page)
        }
        else {
            stateLiveData.value = LiveDataState.InvalidInput
        }
    }

    override fun onFetchPullRequestsSuccess(pullRequestsItem: List<PullRequestItem>) {
        items = pullRequestsItem
        if (items.isEmpty() && page == 1)
            stateLiveData.value = LiveDataState.Empty
        else
            stateLiveData.value = LiveDataState.Success
    }

    override fun onFetchPullRequestsError(e: Throwable) {
        stateLiveData.value = LiveDataState.Error
    }

    fun setInput(input: String) {
        if (input.isNotBlank()) {
            val tokens = input.split("/")
            if (tokens.size == 2) {
                owner = tokens[0]
                repo = tokens[1]
            }
            else {
                clearInput()
            }
        }
        else {
            clearInput()
        }
    }

    fun clearInput() {
        owner = ""
        repo = ""
        items = emptyList()
        page = 1
    }

    fun hasMoreData() = items.size == PER_PAGE

    fun initLiveData() {
        stateLiveData.value = LiveDataState.Null
    }
}