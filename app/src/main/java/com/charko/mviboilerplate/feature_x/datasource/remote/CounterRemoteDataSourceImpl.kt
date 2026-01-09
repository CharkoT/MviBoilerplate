package com.charko.mviboilerplate.feature_x.datasource.remote

import com.charko.mviboilerplate.feature_x.domain.model.Counter
import com.charko.mviboilerplate.feature_x.repository.datasource.CounterDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CounterRemoteDataSourceImpl @Inject constructor(

) : CounterDataSource {
    override suspend fun getCount(): Flow<Counter> {
        TODO("Not yet implemented")
    }

    override suspend fun increaseCounter() {
        TODO("Not yet implemented")
    }

    override suspend fun decreaseCounter() {
        TODO("Not yet implemented")
    }
}