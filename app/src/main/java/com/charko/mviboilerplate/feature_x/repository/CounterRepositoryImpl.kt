package com.charko.mviboilerplate.feature_x.repository

import com.charko.mviboilerplate.feature_x.domain.model.Counter
import com.charko.mviboilerplate.feature_x.domain.repository.CounterRepository
import com.charko.mviboilerplate.di.LocalDataSource
import com.charko.mviboilerplate.di.RemoteDataSource
import com.charko.mviboilerplate.feature_x.repository.datasource.CounterDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CounterRepositoryImpl @Inject constructor(
    @LocalDataSource private val localDataSource: CounterDataSource,
    @RemoteDataSource private val remoteDataSource: CounterDataSource,
) : CounterRepository {
    //    private val _dummyData = MutableStateFlow(Counter(0))
    private var isLocal = true

    override suspend fun getCount(): Flow<Counter> {
        return if (isLocal) {
            localDataSource.getCount()
        } else {
            remoteDataSource.getCount()
        }
    }

    override suspend fun increaseCounter() {
//        delay(500)
//        _dummyData.update { it.copy(value = it.value + 1) }
        if (isLocal) {
            localDataSource.increaseCounter()
        } else {
            remoteDataSource.increaseCounter()
        }
    }

    override suspend fun decreaseCounter() {
//        delay(500)
//        _dummyData.update { it.copy(value = it.value - 1) }

        if (isLocal) {
            localDataSource.decreaseCounter()
        } else {
            remoteDataSource.decreaseCounter()
        }
    }
}