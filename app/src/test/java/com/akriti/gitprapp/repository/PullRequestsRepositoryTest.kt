package com.akriti.gitprapp.repository

import com.akriti.gitprapp.model.GithubPullRequestsServiceResponse
import com.akriti.gitprapp.network.GithubPullRequestsService
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PullRequestsRepositoryTest {

    companion object {
        private const val OWNER = "owner"
        private const val REPO = "repo"
        private const val PER_PAGE = 20
        private const val PAGE = 1
        private const val STATUS = "open"
    }

    private lateinit var subject: PullRequestsRepository

    @Mock
    private lateinit var mockGithubPullRequestsService: GithubPullRequestsService

    @Mock
    private lateinit var mockResponse:  Single<List<GithubPullRequestsServiceResponse>>

    @Before
    fun setUp(){
        subject = PullRequestsRepository(mockGithubPullRequestsService)
    }

    @Test
    fun testGetPullRequests() {
        //GIVEN
        given(mockGithubPullRequestsService.getPullRequests(OWNER, REPO, STATUS, PER_PAGE, PAGE)).willReturn(mockResponse)

        //WHEN
        val response = subject.getPullRequests(OWNER, REPO, STATUS, PER_PAGE, PAGE)

        //THEN
        BDDMockito.then(mockGithubPullRequestsService).should().getPullRequests(OWNER, REPO, STATUS, PER_PAGE, PAGE)
        Assert.assertEquals(mockResponse, response)
    }
}