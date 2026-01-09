package com.charko.mviboilerplate.di

import com.charko.mviboilerplate.feature_x.domain.repository.CounterRepository
import com.charko.mviboilerplate.feature_x.repository.CounterRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCounterRepository(
        impl: CounterRepositoryImpl
    ): CounterRepository
}