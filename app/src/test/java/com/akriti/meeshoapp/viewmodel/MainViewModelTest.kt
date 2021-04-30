package com.akriti.meeshoapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.akriti.meeshoapp.domain.GetPullRequestsUseCaseImpl
import com.akriti.meeshoapp.model.PullRequestItem
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    companion object {
        private const val OWNER = "owner"
        private const val REPO = "repo"
        private const val PER_PAGE = 20
        private const val PAGE = 1
        private const val STATUS = "open"
    }

    @Rule
    @JvmField
    val instantTaskRule = InstantTaskExecutorRule()

    private lateinit var subject: MainViewModel

    @Mock
    private lateinit var mockGetPullRequestsUseCase: GetPullRequestsUseCaseImpl

    @Mock
    private lateinit var mockStateObserver: Observer<MainViewModel.LiveDataState>

    @Mock
    private lateinit var mockThrowable: Throwable

    @Mock
    private lateinit var mockPullRequestsList: List<PullRequestItem>

    @Before
    fun setUp() {
        subject = MainViewModel(
            mockGetPullRequestsUseCase,
            MutableLiveData(),
        )
        subject.stateLiveData.observeForever(mockStateObserver)
    }

    @Test
    fun `fetchPullRequests - usecase invoke - valid input`() {
        //GIVEN
        subject.apply {
            owner = OWNER
            repo = REPO
            page = PAGE
        }

        // WHEN
        subject.fetchPullRequests(true)

        // THEN
        BDDMockito.then(mockGetPullRequestsUseCase).should().execute(OWNER, REPO, STATUS, PER_PAGE, PAGE)
        thenObserverShouldReceiveCorrectStates(MainViewModel.LiveDataState.Loading)
    }

    @Test
    fun `fetchPullRequests - invalid input`() {
        // WHEN
        subject.fetchPullRequests(true)

        // THEN
        thenObserverShouldReceiveCorrectStates(MainViewModel.LiveDataState.InvalidInput)
    }

    @Test
    fun `fetchPullRequests - Success with non empty review list`() {
        // WHEN
        subject.onFetchPullRequestsSuccess(mockPullRequestsList)

        // THEN
        thenObserverShouldReceiveCorrectStates(MainViewModel.LiveDataState.Success)
    }

    @Test
    fun `fetchPullRequests - Success with empty review list and page = 1`() {
        // GIVEN
        subject.page = 1

        // WHEN
        subject.onFetchPullRequestsSuccess(emptyList())

        // THEN
        thenObserverShouldReceiveCorrectStates(MainViewModel.LiveDataState.Empty)
    }

    @Test
    fun `fetchPullRequests - Success with empty reviews and page != 1`() {
        // GIVEN
        subject.page = 6

        // WHEN
        subject.onFetchPullRequestsSuccess(emptyList())

        // THEN
        thenObserverShouldReceiveCorrectStates(MainViewModel.LiveDataState.Success)
    }
    @Test
    fun `fetchPullRequests - Error`() {
        //WHEN
        subject.onFetchPullRequestsError(mockThrowable)

        //THEN
        thenObserverShouldReceiveCorrectStates(MainViewModel.LiveDataState.Error)
    }


    //////////////////////////////////////////////////////////////////////////
    //                                THEN                                  //
    //////////////////////////////////////////////////////////////////////////

    private fun thenObserverShouldReceiveCorrectStates(vararg expected: MainViewModel.LiveDataState) {
        expected.forEach { BDDMockito.then(mockStateObserver).should().onChanged(it) }
    }
}