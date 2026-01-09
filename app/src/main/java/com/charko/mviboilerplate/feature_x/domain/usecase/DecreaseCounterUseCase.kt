package com.charko.mviboilerplate.feature_x.domain.usecase

import com.charko.mviboilerplate.feature_x.domain.repository.CounterRepository
import javax.inject.Inject

class DecreaseCounterUseCase @Inject constructor(
    private val repository: CounterRepository
) {
    suspend operator fun invoke() {
        repository.decreaseCounter()
    }
}