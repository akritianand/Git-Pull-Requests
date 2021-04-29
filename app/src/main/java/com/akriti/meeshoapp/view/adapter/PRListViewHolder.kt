package com.akriti.meeshoapp.view.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.akriti.meeshoapp.R
import com.akriti.meeshoapp.model.PullRequestItem

class PRListViewHolder (
    private val itemView: View
): RecyclerView.ViewHolder(itemView) {

    fun bind(pullRequest: PullRequestItem) {
        itemView.findViewById<TextView>(R.id.pr_title).text = pullRequest.title
        itemView.findViewById<TextView>(R.id.pr_created_at).text = pullRequest.createdAt
        itemView.findViewById<TextView>(R.id.pr_assigne).text = pullRequest.assignee
    }
}