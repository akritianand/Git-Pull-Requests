package com.akriti.gitprapp.domain.injection

import com.akriti.gitprapp.domain.GetPullRequestsUseCase
import com.akriti.gitprapp.domain.GetPullRequestsUseCaseImpl
import com.akriti.gitprapp.repository.injection.PullRequestsRepositoryModule
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module (includes = [
    PullRequestsRepositoryModule::class
])
class GetPullRequestsUseCaseModule {

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    fun provideGetPullRequestsUseCase(useCase: GetPullRequestsUseCaseImpl): GetPullRequestsUseCase = useCase
}