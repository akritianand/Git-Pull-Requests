package com.akriti.meeshoapp.domain

import com.akriti.meeshoapp.dependencies.annotations.qualifiers.ForComputationThread
import com.akriti.meeshoapp.dependencies.annotations.qualifiers.ForIoThread
import com.akriti.meeshoapp.dependencies.annotations.qualifiers.ForMainThread
import com.akriti.meeshoapp.model.GithubPullRequestsServiceResponse
import com.akriti.meeshoapp.model.PullRequestItem
import com.akriti.meeshoapp.repository.PullRequestsRepository
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class GetPullRequestsUseCaseImpl @Inject constructor(
    private val compositeDisposable: CompositeDisposable,
    private val pullRequestsRepository: PullRequestsRepository,
    @ForIoThread private val ioScheduler: Scheduler,
    @ForComputationThread private val computationScheduler: Scheduler,
    @ForMainThread private val mainScheduler: Scheduler,
): GetPullRequestsUseCase {

    private lateinit var callback: GetPullRequestsUseCase.Callback

    override fun setCallback(callback: GetPullRequestsUseCase.Callback) {
        this.callback = callback
    }
    override fun execute(owner: String, repo: String, status: String) {
        trackDisposable(pullRequestsRepository.getPullRequests(owner, repo, status)
            .subscribeOn(ioScheduler)
            .observeOn(computationScheduler)
            .map (::mapResponse)
            .observeOn(mainScheduler)
            .subscribe(::onSuccess, ::onError)
        )
    }

    private fun onSuccess(response: List<PullRequestItem>) {
        callback.onFetchPullRequestsSuccess(response)
    }

    private fun onError(error: Throwable) {
        callback.onFetchPullRequestsError(error)
    }

    private fun trackDisposable(disposable: Disposable): Disposable {
        compositeDisposable.add(disposable)
        return disposable
    }

    override fun cleanup() {
        compositeDisposable.clear()
    }

    private fun mapResponse(response: List<GithubPullRequestsServiceResponse>): List<PullRequestItem> {
        val result = mutableListOf<PullRequestItem>()
        response.forEach {
            result.add(
                PullRequestItem(
                    title = it.title,
                    createdAt = it.created_at,
                    assignee = it.assignee?.login?.orEmpty()
            ))
        }

        return result
    }
}