package com.akriti.meeshoapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akriti.meeshoapp.R
import com.akriti.meeshoapp.model.PullRequestItem
import javax.inject.Inject

class PRListAdapter @Inject constructor(
    private val layoutInflater: LayoutInflater
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: List<PullRequestItem> = emptyList()

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = layoutInflater.inflate(R.layout.item_pull_request, parent, false)
        return PRListViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as? PRListViewHolder
        viewHolder?.bind(items[position])
    }
}