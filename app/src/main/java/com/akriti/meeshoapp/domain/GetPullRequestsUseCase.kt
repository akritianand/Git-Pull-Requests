package com.akriti.meeshoapp.domain

import com.akriti.meeshoapp.model.PullRequestItem

interface GetPullRequestsUseCase {

    fun setCallback(callback: Callback)

    interface Callback {
        fun onFetchPullRequestsSuccess(pullRequestsItem: List<PullRequestItem>)
        fun onFetchPullRequestsError(e: Throwable)
    }

    fun execute(owner: String, repo: String, status: String, perPage: Int, page: Int)

    fun cleanup()
}