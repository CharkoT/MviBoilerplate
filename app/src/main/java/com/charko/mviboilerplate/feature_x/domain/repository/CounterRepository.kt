package com.charko.mviboilerplate.feature_x.domain.repository

import com.charko.mviboilerplate.feature_x.domain.model.Counter
import kotlinx.coroutines.flow.Flow

interface CounterRepository {
    suspend fun getCount(): Flow<Counter>
    suspend fun increaseCounter()
    suspend fun decreaseCounter()
}