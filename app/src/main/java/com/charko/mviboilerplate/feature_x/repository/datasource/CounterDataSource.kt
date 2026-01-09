package com.charko.mviboilerplate.feature_x.repository.datasource

import com.charko.mviboilerplate.feature_x.domain.model.Counter
import kotlinx.coroutines.flow.Flow

interface CounterDataSource {
    suspend fun getCount(): Flow<Counter>
    suspend fun increaseCounter()
    suspend fun decreaseCounter()
}