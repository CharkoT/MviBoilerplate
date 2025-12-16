package com.charko.mviboilerplate.domain.usecase

import com.charko.mviboilerplate.domain.repository.CounterRepository
import javax.inject.Inject

class IncreaseCounterUseCase @Inject constructor(private val repository: CounterRepository) {
    suspend operator fun invoke() {
        repository.increaseCounter()
    }
}