package com.akriti.meeshoapp.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.akriti.meeshoapp.R
import com.akriti.meeshoapp.databinding.PrListFragmentBinding
import com.akriti.meeshoapp.databinding.SearchFragmentBinding
import com.akriti.meeshoapp.utils.OnClickHandler
import com.akriti.meeshoapp.viewmodel.MainViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class SearchFragment : Fragment(), OnClickHandler {

    companion object {
        fun newInstance() = SearchFragment()
    }

    @Inject
    lateinit var viewModel: MainViewModel

    private lateinit var dataBinding: SearchFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        AndroidSupportInjection.inject(this)
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.search_fragment, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.clickListener = this
        viewModel.initLiveData()
        viewModel.stateLiveData.observe(viewLifecycleOwner, ::onFetchPullRequestResult)
        setUpSearch(view)
    }

    override fun onClickListener() {
        viewModel.setInput(dataBinding.searchInputField.text.toString())
        closeKeyboard(dataBinding.searchInputField)
        viewModel.fetchPullRequests(true)
    }

    private fun setUpSearch(view: View) {
        viewModel.clearInput()
        dataBinding.shouldEnableInput = true
        dataBinding.showError = false
        dataBinding.showLoading = false
        dataBinding.showInvalidInputError = false
    }

    private fun onFetchPullRequestResult(state: MainViewModel.LiveDataState) {
        when (state) {
            MainViewModel.LiveDataState.Loading -> {
                dataBinding.showLoading = true
                dataBinding.shouldEnableInput = false
                dataBinding.showInvalidInputError = false
                dataBinding.showError = false
            }
            MainViewModel.LiveDataState.Success, MainViewModel.LiveDataState.Empty -> {
                dataBinding.shouldEnableInput = true
                dataBinding.showInvalidInputError = false
                dataBinding.showError = false
                dataBinding.showLoading = false
                if (requireActivity().supportFragmentManager.findFragmentByTag(PRListFragment.javaClass.name) == null) {
                    requireActivity().supportFragmentManager.beginTransaction()
                            .add(R.id.container, PRListFragment.newInstance(), PRListFragment.javaClass.name)
                            .addToBackStack(null)
                            .commit()
                }
            }
            MainViewModel.LiveDataState.InvalidInput -> {
                dataBinding.shouldEnableInput = true
                dataBinding.showInvalidInputError = true
                dataBinding.showError = false
                dataBinding.showLoading = false
            }
            MainViewModel.LiveDataState.Error -> {
                dataBinding.shouldEnableInput = true
                dataBinding.showInvalidInputError = false
                dataBinding.showError = true
                dataBinding.showLoading = false
            }
        }
    }

    private fun closeKeyboard(editText: EditText) {
        val inputMethodManager = editText.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(editText.windowToken, 0)
    }
}