package com.akriti.meeshoapp.view

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ViewFlipper
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.akriti.meeshoapp.R
import com.akriti.meeshoapp.databinding.PrListFragmentBinding
import com.akriti.meeshoapp.utils.OnClickHandler
import com.akriti.meeshoapp.view.adapter.PRListAdapter
import com.akriti.meeshoapp.utils.Paginator
import com.akriti.meeshoapp.viewmodel.MainViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class PRListFragment : Fragment(), OnClickHandler {

    @Inject
    lateinit var recyclerViewAdapter: PRListAdapter

    @Inject
    lateinit var paginator: Paginator

    @Inject
    lateinit var viewModel: MainViewModel

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    private lateinit var dataBinding: PrListFragmentBinding

    companion object {
        fun newInstance() = PRListFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.slide_right)
        exitTransition = inflater.inflateTransition(R.transition.slide_left)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        AndroidSupportInjection.inject(this)
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.pr_list_fragment, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.viewmodel = viewModel
        dataBinding.clickListener = this
        viewModel.stateLiveData.observe(viewLifecycleOwner, ::onFetchPullRequestResult)
        setupRecyclerView()
    }

    override fun onClickListener() {
        requireActivity().supportFragmentManager.popBackStack()
    }

    private fun setupRecyclerView() {
        dataBinding.prRecyclerView.apply {
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
                dataBinding.viewFlipper.displayedChild = 1
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