package com.akriti.gitprapp.domain

import com.akriti.gitprapp.dependencies.annotations.qualifiers.ForComputationThread
import com.akriti.gitprapp.dependencies.annotations.qualifiers.ForIoThread
import com.akriti.gitprapp.dependencies.annotations.qualifiers.ForMainThread
import com.akriti.gitprapp.model.GithubPullRequestsServiceResponse
import com.akriti.gitprapp.model.PullRequestItem
import com.akriti.gitprapp.repository.PullRequestsRepository
import com.akriti.gitprapp.utils.toFormattedDate
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
    override fun execute(owner: String, repo: String, status: String, perPage: Int, page: Int) {
        trackDisposable(pullRequestsRepository.getPullRequests(owner, repo, status, perPage, page)
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
                    prNo = it.number,
                    title = it.title,
                    createdAt = it.created_at.toFormattedDate(),
                    createdBy = it.user.login
            ))
        }

        return result
    }
}