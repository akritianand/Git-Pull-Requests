package com.akriti.gitprapp.repository

import com.akriti.gitprapp.model.GithubPullRequestsServiceResponse
import com.akriti.gitprapp.network.GithubPullRequestsService
import io.reactivex.Single
import javax.inject.Inject

class PullRequestsRepository @Inject constructor(
    private val githubPullRequestsService: GithubPullRequestsService
){

    fun getPullRequests(owner: String, repo: String, status: String, perPage: Int, page: Int): Single<List<GithubPullRequestsServiceResponse>> =
        githubPullRequestsService.getPullRequests(owner, repo, status, perPage, page)
}