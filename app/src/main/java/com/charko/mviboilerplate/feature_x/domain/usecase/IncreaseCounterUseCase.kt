package com.charko.mviboilerplate.feature_x.domain.usecase

import com.charko.mviboilerplate.feature_x.domain.repository.CounterRepository
import javax.inject.Inject

class IncreaseCounterUseCase @Inject constructor(
    private val repository: CounterRepository
) {
    suspend operator fun invoke() {
        repository.increaseCounter()
    }
}