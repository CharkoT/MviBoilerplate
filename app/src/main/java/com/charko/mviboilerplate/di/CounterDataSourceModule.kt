package com.charko.mviboilerplate.di

import com.charko.mviboilerplate.feature_x.datasource.local.CounterLocalDataSourceImpl
import com.charko.mviboilerplate.feature_x.datasource.remote.CounterRemoteDataSourceImpl
import com.charko.mviboilerplate.feature_x.repository.datasource.CounterDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CounterDataSourceModule {
    @Binds
    @LocalDataSource
    abstract fun bindLocalDataSource(impl: CounterLocalDataSourceImpl): CounterDataSource

    @Binds
    @RemoteDataSource
    abstract fun bindRemoteDataSource(impl: CounterRemoteDataSourceImpl): CounterDataSource
}