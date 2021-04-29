package com.akriti.meeshoapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akriti.meeshoapp.R
import com.akriti.meeshoapp.view.adapter.PRListAdapter
import com.akriti.meeshoapp.view.utils.Paginator
import com.akriti.meeshoapp.viewmodel.MainViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class PRListFragment : Fragment() {

    @Inject
    lateinit var recyclerViewAdapter: PRListAdapter

    @Inject
    lateinit var paginator: Paginator

    @Inject
    lateinit var viewModel: MainViewModel

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    companion object {
        fun newInstance() = PRListFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        AndroidSupportInjection.inject(this)
        return inflater.inflate(R.layout.pr_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchPullRequestsResultLiveData.observe(viewLifecycleOwner, ::onFetchPullRequestResult)
        setupRecyclerView(view)
    }

    private fun setupRecyclerView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.pr_recycler_view)

        recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = recyclerViewAdapter
            paginator.bind(this) { viewModel.fetchPullRequests() }
        }
        recyclerViewAdapter.items = viewModel.items
    }

    private fun onFetchPullRequestResult (state: MainViewModel.LiveDataState) {
        when (state) {
            MainViewModel.LiveDataState.Success -> {
                recyclerViewAdapter.items = viewModel.items
            }
            MainViewModel.LiveDataState.Error -> {}
        }
    }
}