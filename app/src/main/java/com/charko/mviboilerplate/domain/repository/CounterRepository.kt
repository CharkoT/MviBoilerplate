package com.charko.mviboilerplate.domain.repository

import com.charko.mviboilerplate.domain.model.Counter
import kotlinx.coroutines.flow.Flow

interface CounterRepository {
    fun getCount(): Flow<Counter>
    suspend fun increaseCounter()
    suspend fun decreaseCounter()
}