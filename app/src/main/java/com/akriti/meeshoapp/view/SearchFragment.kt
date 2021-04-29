package com.akriti.meeshoapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.LiveData
import com.akriti.meeshoapp.R
import com.akriti.meeshoapp.viewmodel.MainViewModel
import dagger.android.support.AndroidSupportInjection
import java.lang.Error
import javax.inject.Inject

class SearchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchFragment()
    }

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        AndroidSupportInjection.inject(this)
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchPullRequestsResultLiveData.observe(viewLifecycleOwner, ::onFetchPullRequestResult)
        setUpSearch(view)
    }

    private fun setUpSearch(view: View) {
        viewModel.clearInput()
        view.findViewById<Button>(R.id.search_confirm).setOnClickListener {
            val input = view.findViewById<EditText>(R.id.search_input_field).text.toString()
            viewModel.setInput(input)
            viewModel.fetchPullRequests()
        }
    }

    private fun onFetchPullRequestResult (state: MainViewModel.LiveDataState) {
        when (state) {
            MainViewModel.LiveDataState.Success -> {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.container, PRListFragment.newInstance())
                    .addToBackStack(null)
                    .commit()
            }
            MainViewModel.LiveDataState.Error -> {}
        }
    }

}