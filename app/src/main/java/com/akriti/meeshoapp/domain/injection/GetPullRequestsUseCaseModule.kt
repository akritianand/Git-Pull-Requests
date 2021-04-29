package com.akriti.meeshoapp.domain.injection

import com.akriti.base.injection.annotations.scopes.PerActivity
import com.akriti.base.injection.annotations.scopes.PerFragment
import com.akriti.meeshoapp.domain.GetPullRequestsUseCase
import com.akriti.meeshoapp.domain.GetPullRequestsUseCaseImpl
import com.akriti.meeshoapp.repository.injection.PullRequestsRepositoryModule
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module (includes = [
    PullRequestsRepositoryModule::class
])
class GetPullRequestsUseCaseModule {

    @Provides
    @PerFragment
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    @PerFragment
    fun provideGetPullRequestsUseCase(useCase: GetPullRequestsUseCaseImpl): GetPullRequestsUseCase = useCase
}