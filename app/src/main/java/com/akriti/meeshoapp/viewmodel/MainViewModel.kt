package com.akriti.meeshoapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akriti.meeshoapp.domain.GetPullRequestsUseCase
import com.akriti.meeshoapp.domain.GetPullRequestsUseCaseImpl
import com.akriti.meeshoapp.model.DisplayableItem
import com.akriti.meeshoapp.model.PullRequestItem
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

    private var page = 1
    private var owner = ""
    private var repo = ""

    var items = emptyList<DisplayableItem>()

    sealed class LiveDataState {
        object Null: LiveDataState()
        object Loading: LiveDataState()
        object Success: LiveDataState()
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

    fun getOwner() = owner

    fun getRepo() = repo
}