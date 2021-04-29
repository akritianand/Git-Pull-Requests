package com.akriti.meeshoapp.model

import com.google.gson.annotations.SerializedName

data class GithubPullRequestsServiceResponse (
    @SerializedName("id") val id : Int,
    @SerializedName("html_url") val html_url : String,
    @SerializedName("number") val number : Int,
    @SerializedName("state") val state : String,
    @SerializedName("title") val title : String,
    @SerializedName("user") val user : User,
    @SerializedName("created_at") val created_at : String,
    @SerializedName("updated_at") val updated_at : String,
    @SerializedName("closed_at") val closed_at : String
)

data class User (
    @SerializedName("login") val login : String,
    @SerializedName("html_url") val html_url : String,
)