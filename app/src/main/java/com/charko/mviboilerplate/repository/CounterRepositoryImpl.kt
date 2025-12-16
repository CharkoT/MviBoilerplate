package com.charko.mviboilerplate.repository

import com.charko.mviboilerplate.domain.model.Counter
import com.charko.mviboilerplate.domain.repository.CounterRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class CounterRepositoryImpl @Inject constructor() : CounterRepository {
    private val _dummyData = MutableStateFlow(Counter(0))

    override fun getCount(): Flow<Counter> = _dummyData

    override suspend fun increaseCounter() {
        delay(500)
        _dummyData.update { it.copy(value = it.value + 1) }
    }

    override suspend fun decreaseCounter() {
        delay(500)
        _dummyData.update { it.copy(value = it.value - 1) }
    }
}