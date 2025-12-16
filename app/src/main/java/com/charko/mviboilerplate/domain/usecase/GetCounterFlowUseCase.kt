package com.charko.mviboilerplate.domain.usecase

import com.charko.mviboilerplate.domain.model.Counter
import com.charko.mviboilerplate.domain.repository.CounterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCounterFlowUseCase @Inject constructor(private val repository: CounterRepository) {
    operator fun invoke(): Flow<Counter> = repository.getCount()
}