package com.akriti.gitprapp.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.akriti.gitprapp.R
import com.akriti.gitprapp.databinding.ItemPullRequestBinding
import com.akriti.gitprapp.model.PullRequestItem

class PRListViewHolder (
    private val dataBinding: ItemPullRequestBinding
): RecyclerView.ViewHolder(dataBinding.root) {

    fun bind(pullRequest: PullRequestItem) {
        dataBinding.apply {
            prTitle.text = itemView.resources.getString(R.string.pr_item_title, pullRequest.prNo, pullRequest.title)
            prCreatedAt.text = itemView.resources.getString(R.string.pr_item_created_date, pullRequest.createdAt)
            prCreatedBy.text = itemView.resources.getString(R.string.pr_item_created_by, pullRequest.createdBy)
        }
    }
}