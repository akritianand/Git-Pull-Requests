package com.akriti.meeshoapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.ViewFlipper
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akriti.meeshoapp.R
import com.akriti.meeshoapp.view.adapter.PRListAdapter
import com.akriti.meeshoapp.utils.Paginator
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

    private lateinit var viewFlipper: ViewFlipper

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
        viewModel.stateLiveData.observe(viewLifecycleOwner, ::onFetchPullRequestResult)
        viewFlipper = view.findViewById(R.id.view_flipper)
        setupToolbar()
        setupBackButton()
        setupRecyclerView()
    }

    private fun setupToolbar() {
        viewFlipper.currentView.findViewById<TextView>(R.id.repository)?.text = resources.getString(
            R.string.pr_list_fragment_repository, viewModel.owner, viewModel.repo)
    }

    private fun setupBackButton() {
        viewFlipper.currentView.findViewById<ImageView>(R.id.back_button)?.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container, SearchFragment.newInstance())
                .addToBackStack(null)
                .commit()
        }
    }

    private fun setupRecyclerView() {
        val recyclerView = viewFlipper.currentView.findViewById<RecyclerView>(R.id.pr_recycler_view)
        recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = recyclerViewAdapter
            paginator.bind(this) { viewModel.fetchPullRequests() }
        }
    }

    private fun onFetchPullRequestResult (state: MainViewModel.LiveDataState) {
        when (state) {
            MainViewModel.LiveDataState.Loading -> {
                paginator.isLoading = true
                shouldShowError(false)
                recyclerViewAdapter.shouldShowLoading(true)
            }
            MainViewModel.LiveDataState.Empty -> {
                viewFlipper.displayedChild = 1
                setupToolbar()
                setupBackButton()
            }
            MainViewModel.LiveDataState.Success -> {
                paginator.isLoading = false
                shouldShowError(false)
                recyclerViewAdapter.apply {
                    shouldShowLoading(false)
                    setContent(viewModel.items)
                }
                paginator.hasMoreDataAvailable = viewModel.hasMoreData()
            }
            MainViewModel.LiveDataState.Error -> {
                paginator.isLoading = false
                recyclerViewAdapter.shouldShowLoading(false)
                shouldShowError(true)
            }
        }
    }

    private fun shouldShowError(show: Boolean) {
        paginator.isError = show
        if (show) {
            recyclerViewAdapter.showError { viewModel.fetchPullRequests() }
        }
        else
            recyclerViewAdapter.hideError()
    }
}