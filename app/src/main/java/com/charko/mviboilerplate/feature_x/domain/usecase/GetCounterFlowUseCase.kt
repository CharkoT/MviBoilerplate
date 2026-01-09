package com.charko.mviboilerplate.feature_x.domain.usecase

import com.charko.mviboilerplate.feature_x.domain.model.Counter
import com.charko.mviboilerplate.feature_x.domain.repository.CounterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCounterFlowUseCase @Inject constructor(
    private val repository: CounterRepository
) {
    suspend operator fun invoke(): Flow<Counter> = repository.getCount()
}