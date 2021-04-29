package com.akriti.meeshoapp.network

import com.akriti.meeshoapp.model.GithubPullRequestsServiceResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubPullRequestsService {

    @GET("/repos/{owner}/{repo}/pulls")
    fun getPullRequests(@Path("owner") owner: String, @Path("repo") repo: String, @Query("status") status: String):
            Single<List<GithubPullRequestsServiceResponse>>
}