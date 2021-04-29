package com.akriti.meeshoapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akriti.meeshoapp.R
import com.akriti.meeshoapp.model.DisplayableItem
import com.akriti.meeshoapp.model.ErrorItem
import com.akriti.meeshoapp.model.LoadingItem
import com.akriti.meeshoapp.model.PullRequestItem
import javax.inject.Inject

class PRListAdapter @Inject constructor(
    private val layoutInflater: LayoutInflater
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: MutableList<DisplayableItem> = mutableListOf()

    companion object {
        private const val PULL_REQUEST = 0
        private const val LOADING = 1
        private const val ERROR = 2
    }

    fun setContent(content: List<DisplayableItem>) {
        items.addAll(content)
        notifyDataSetChanged()
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        return when(items[position]) {
            is PullRequestItem -> PULL_REQUEST
            is LoadingItem -> LOADING
            else -> ERROR
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            PULL_REQUEST -> {
                val view = layoutInflater.inflate(R.layout.item_pull_request, parent, false)
                PRListViewHolder(view)
            }
            LOADING -> {
                val view = layoutInflater.inflate(R.layout.item_recycler_view_loading, parent, false)
                LoadingViewHolder(view)
            }
            else -> {
                val view = layoutInflater.inflate(R.layout.item_recycler_view_error, parent, false)
                ErrorViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            PULL_REQUEST -> {
                val viewHolder = holder as? PRListViewHolder
                viewHolder?.bind(items[position] as PullRequestItem)
            }
            ERROR -> {
                val viewHolder = holder as? ErrorViewHolder
                viewHolder?.setClickListener ( (items[position] as ErrorItem).action )
            }
        }
    }

    fun shouldShowLoading(show: Boolean) {
        if (items.isEmpty())
            return

        if (show) {
            items.add(LoadingItem())
        }
        else {
            while (items.last() is LoadingItem) {
                items.removeLast()
            }
        }
        notifyDataSetChanged()
    }

    fun showError (action: () -> Unit) {
        if (items.isEmpty())
            return

        if (items.last() !is ErrorItem) {
            items.add(ErrorItem(action))
        }
        notifyDataSetChanged()
    }

    fun hideError() {
        if (items.isEmpty())
            return

        while (items.last() is ErrorItem) {
            items.removeLast()
        }
        notifyDataSetChanged()
    }
}