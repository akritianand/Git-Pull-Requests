package com.akriti.meeshoapp.repository

import com.akriti.meeshoapp.model.GithubPullRequestsServiceResponse
import com.akriti.meeshoapp.network.GithubPullRequestsService
import io.reactivex.Single
import javax.inject.Inject

class PullRequestsRepository @Inject constructor(
    private val githubPullRequestsService: GithubPullRequestsService
){

    fun getPullRequests(owner: String, repo: String, status: String, perPage: Int, page: Int): Single<List<GithubPullRequestsServiceResponse>> =
        githubPullRequestsService.getPullRequests(owner, repo, status, perPage, page)
}