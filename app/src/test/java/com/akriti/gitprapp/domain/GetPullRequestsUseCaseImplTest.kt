package com.akriti.gitprapp.domain

import com.akriti.gitprapp.model.GithubPullRequestsServiceResponse
import com.akriti.gitprapp.model.PullRequestItem
import com.akriti.gitprapp.repository.PullRequestsRepository
import com.google.gson.GsonBuilder
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetPullRequestsUseCaseImplTest {

    companion object {
        private const val OWNER = "octocat"
        private const val REPO = "Hello-World"
        private const val PER_PAGE = 20
        private const val PAGE = 1
        private const val STATUS = "open"
    }

    private lateinit var subject: GetPullRequestsUseCaseImpl

    private lateinit var pullRequestResponse: List<GithubPullRequestsServiceResponse>

    @Mock
    private lateinit var mockPullRequestsRepository: PullRequestsRepository

    @Mock
    private lateinit var mockCompositeDisposable: CompositeDisposable

    @Mock
    private lateinit var mockCallback: GetPullRequestsUseCase.Callback

    @Mock
    private lateinit var mockThrowable: Throwable

    @Before
    fun setUp() {
        subject = GetPullRequestsUseCaseImpl(
            mockCompositeDisposable,
            mockPullRequestsRepository,
            Schedulers.trampoline(),
            Schedulers.trampoline(),
            Schedulers.trampoline()

        )
        subject.setCallback(mockCallback)
    }

    @Test
    fun `execute - success`() {
        //GIVEN
        givenSearchResponse("pull_request_response.json")
        BDDMockito.given(mockPullRequestsRepository.getPullRequests(OWNER, REPO, STATUS, PER_PAGE, PAGE)).willReturn(Single.just(pullRequestResponse))

        //WHEN
        subject.execute(OWNER, REPO, STATUS, PER_PAGE, PAGE)

        //THEN
        BDDMockito.then(mockPullRequestsRepository).should().getPullRequests(OWNER, REPO, STATUS, PER_PAGE, PAGE)
        BDDMockito.then(mockCallback).should().onFetchPullRequestsSuccess(listOf(PullRequestItem(1347, "Amazing new feature", "26 Jan 2011", "octocat")))
    }

    @Test
    fun `execute - error`() {
        //GIVEN
        BDDMockito.given(mockPullRequestsRepository.getPullRequests(OWNER, REPO, STATUS, PER_PAGE, PAGE)).willReturn(Single.error(mockThrowable))

        //WHEN
        subject.execute(OWNER, REPO, STATUS, PER_PAGE, PAGE)

        //THEN
        BDDMockito.then(mockPullRequestsRepository).should().getPullRequests(OWNER, REPO, STATUS, PER_PAGE, PAGE)
        BDDMockito.then(mockCallback).should().onFetchPullRequestsError(mockThrowable)
    }

    //////////////////////////////////////////////////////////////////////////
    //                                GIVEN                                 //
    //////////////////////////////////////////////////////////////////////////

    private fun givenSearchResponse(fileName: String) {
        val classLoader: ClassLoader? = Array<GithubPullRequestsServiceResponse>::class.java.classLoader
        val line = classLoader?.getResource(fileName)?.readText()
        pullRequestResponse = GsonBuilder().create().fromJson(line, Array<GithubPullRequestsServiceResponse>::class.java).toList()
    }
}