package com.akriti.gitprapp.view.injection.fragment

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.akriti.gitprapp.dependencies.annotations.qualifiers.ForFragment
import com.akriti.gitprapp.dependencies.annotations.scopes.PerFragment
import com.akriti.gitprapp.domain.injection.GetPullRequestsUseCaseModule
import com.akriti.gitprapp.view.PRListFragment
import com.akriti.gitprapp.view.adapter.PRListAdapter
import com.akriti.gitprapp.utils.Paginator
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