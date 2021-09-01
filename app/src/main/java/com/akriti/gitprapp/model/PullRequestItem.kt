package com.akriti.gitprapp.model

data class PullRequestItem(
    val prNo: Int,
    val title: String,
    val createdAt: String,
    val createdBy: String
): DisplayableItem
