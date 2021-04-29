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

        private const val SEARCH_TEXT_KEY = "search_text_key"
    }

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        AndroidSupportInjection.inject(this)
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        with(viewModel) {
//            if (searchText.isNotEmpty()) {
//                outState.putString(SEARCH_TEXT_KEY, searchText)
//            }
//        }
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchPullRequestsResultLiveData.observe(viewLifecycleOwner, ::onFetchPullRequestResult)
        setUpSearch(view)
        handleSearchState(savedInstanceState)
    }

    private fun setUpSearch(view: View) {
        view.findViewById<Button>(R.id.search_confirm).setOnClickListener {
            val input = view.findViewById<EditText>(R.id.search_input_field).text.toString()
            viewModel.fetchPullRequests(input)
        }
    }

    private fun handleSearchState(savedInstanceState: Bundle?) {
//        val searchText = savedInstanceState?.getString(SEARCH_TEXT_KEY)
//        if (searchText.isNotBlank()) {
//            instoreSearchBarWidget.setSearchText(searchText)
//            viewModel.loadFreshSearch(searchText, leanPlumApplicationManager.instoreSearchAlgorithmVariant.value(), orionReleaseLeanplumManager.isStockTest, searchArgument?.shoppingListId)
//        }
    }

    private fun onFetchPullRequestResult (state: MainViewModel.LiveDataState) {
        when (state) {
            is MainViewModel.LiveDataState.Success -> {}
            MainViewModel.LiveDataState.Error -> {}
        }
    }

}