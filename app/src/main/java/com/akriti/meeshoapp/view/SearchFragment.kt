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
import androidx.fragment.app.Fragment
import com.akriti.meeshoapp.R
import com.akriti.meeshoapp.viewmodel.MainViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class SearchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchFragment()
    }

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        AndroidSupportInjection.inject(this)
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.initLiveData()
        viewModel.stateLiveData.observe(viewLifecycleOwner, ::onFetchPullRequestResult)
        setUpSearch(view)
    }

    private fun setUpSearch(view: View) {
        viewModel.clearInput()
        shouldEnableInput(true)
        shouldShowErrorMessage(false)
        shouldShowLoadingIndicator(false)

        view.findViewById<Button>(R.id.search_confirm).setOnClickListener {
            val editText = view.findViewById<EditText>(R.id.search_input_field)
            viewModel.setInput(editText.text.toString())
            closeKeyboard(editText)
            viewModel.fetchPullRequests(true)
        }
    }

    private fun onFetchPullRequestResult(state: MainViewModel.LiveDataState) {
        when (state) {
            MainViewModel.LiveDataState.Loading -> {
                shouldShowLoadingIndicator(true)
                shouldEnableInput(false)
                shouldShowInvalidInputMessage(false)
                shouldShowErrorMessage(false)
            }
            MainViewModel.LiveDataState.Success, MainViewModel.LiveDataState.Empty -> {
                shouldShowInvalidInputMessage(false)
                shouldShowErrorMessage(false)
                shouldShowLoadingIndicator(false)
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.container, PRListFragment.newInstance())
                    .addToBackStack(null)
                    .commit()
            }
            MainViewModel.LiveDataState.InvalidInput -> {
                shouldEnableInput(true)
                shouldShowInvalidInputMessage(true)
                shouldShowErrorMessage(false)
                shouldShowLoadingIndicator(false)
            }
            MainViewModel.LiveDataState.Error -> {
                shouldEnableInput(true)
                shouldShowInvalidInputMessage(false)
                shouldShowErrorMessage(true)
                shouldShowLoadingIndicator(false)
            }
        }
    }

    private fun closeKeyboard(editText: EditText) {
        val inputMethodManager = editText.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(editText.windowToken, 0)
    }

    private fun shouldEnableInput(enable: Boolean) {
        view?.findViewById<EditText>(R.id.search_input_field)?.isEnabled = enable
        view?.findViewById<Button>(R.id.search_confirm)?.isEnabled = enable
    }

    private fun shouldShowErrorMessage(show: Boolean) {
        if (show)
            view?.findViewById<TextView>(R.id.error_message)?.visibility = View.VISIBLE
        else
            view?.findViewById<TextView>(R.id.error_message)?.visibility = View.GONE
    }

    private fun shouldShowLoadingIndicator(show: Boolean) {
        if (show)
            view?.findViewById<ProgressBar>(R.id.search_in_progress)?.visibility = View.VISIBLE
        else
            view?.findViewById<ProgressBar>(R.id.search_in_progress)?.visibility = View.GONE
    }

    private fun shouldShowInvalidInputMessage(show: Boolean) {
        if (show)
            view?.findViewById<TextView>(R.id.invalid_input_message)?.visibility = View.VISIBLE
        else
            view?.findViewById<TextView>(R.id.invalid_input_message)?.visibility = View.GONE
    }

}