package com.akriti.gitprapp.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import javax.inject.Inject

class Paginator @Inject constructor(
    private val loadingTriggerThreshold: Int
) {
    var recyclerView: RecyclerView? = null
    var loadMoreCallback: () -> Unit = {}
    var isLoading: Boolean = false
    var hasMoreDataAvailable = false
    var isError: Boolean = false

    private val onScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            if (dy > 0) { //To check for scroll down
                checkEndOffset(recyclerView) // Each time when list is scrolled check if end of the list is reached
            }
        }
    }

    fun bind(viewGroup: RecyclerView, loadMoreCallback: () -> Unit) {
        this.loadMoreCallback = loadMoreCallback
        recyclerView = viewGroup

        // Attach scrolling listener in order to perform end offset check on each scroll event
        recyclerView?.addOnScrollListener(onScrollListener)

        // Trigger initial check since adapter might not have any items initially so no scrolling events upon
        // RecyclerView (that triggers check) will occur
        checkEndOffset(viewGroup)
    }

    fun unbind() {
        loadMoreCallback = {}
        recyclerView?.removeOnScrollListener(onScrollListener)
    }

    private fun checkEndOffset(recyclerView: RecyclerView) {

        val visibleItemCount = recyclerView.childCount
        val layoutManager = recyclerView.layoutManager
        val totalItemCount = layoutManager?.itemCount ?: 0

        val firstVisibleItemPosition = (layoutManager as? LinearLayoutManager)?.findFirstVisibleItemPosition() ?: 0

        // Check if end of the list is reached (counting threshold) or if there is no items at all
        if (totalItemCount - visibleItemCount <= firstVisibleItemPosition + loadingTriggerThreshold || totalItemCount == 0) {
            // Call load more only if loading is not currently in progress and if there is more items to load
            if (!isLoading && hasMoreDataAvailable && !isError) {
                loadMoreCallback()
            }
        }
    }
}