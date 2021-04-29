package com.akriti.meeshoapp.view.injection.fragment

import android.content.Context
import android.view.LayoutInflater
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akriti.base.injection.annotations.qualifiers.ForFragment
import com.akriti.base.injection.annotations.scopes.PerFragment
import com.akriti.meeshoapp.domain.injection.GetPullRequestsUseCaseModule
import com.akriti.meeshoapp.view.PRListFragment
import com.akriti.meeshoapp.view.adapter.PRListAdapter
import com.akriti.meeshoapp.view.utils.Paginator
import com.akriti.meeshoapp.viewmodel.MainViewModel
import dagger.Module
import dagger.Provides

@Module(includes = [
    GetPullRequestsUseCaseModule::class
])
class PRListFragmentModule {
    @Provides
    @PerFragment
    @ForFragment
    fun providePRListFragmentContext(fragment: PRListFragment): Context = fragment.requireContext()

    @Provides
    @PerFragment
    internal fun provideLayoutInflater(@ForFragment context: Context) = LayoutInflater.from(context)

    @Provides
    @PerFragment
    fun provideLinearLayoutManager(@ForFragment context: Context) = LinearLayoutManager(context)

    @Provides
    @PerFragment
    fun provideRecyclerViewPaginator(loadingTriggerThreshold: Int): Paginator =
        Paginator(loadingTriggerThreshold)

    @Provides
    fun provideLoadingTriggerThresholdValue() = 5

    @Provides
    @PerFragment
    fun providePLPListAdapter(layoutInflater: LayoutInflater) = PRListAdapter(layoutInflater)
}