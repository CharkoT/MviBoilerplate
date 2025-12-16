package com.charko.mviboilerplate.presentation

import androidx.lifecycle.viewModelScope
import com.charko.mviboilerplate.base.BaseViewModel
import com.charko.mviboilerplate.domain.usecase.DecreaseCounterUseCase
import com.charko.mviboilerplate.domain.usecase.GetCounterFlowUseCase
import com.charko.mviboilerplate.domain.usecase.IncreaseCounterUseCase
import com.charko.mviboilerplate.presentation.contact.MainUiEffect
import com.charko.mviboilerplate.presentation.contact.MainUiEvent
import com.charko.mviboilerplate.presentation.contact.MainUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val increaseCounterUseCase: IncreaseCounterUseCase,
    private val decreaseCounterUseCase: DecreaseCounterUseCase,
    private val getCounterFlowUseCase: GetCounterFlowUseCase,
) : BaseViewModel<MainUiState, MainUiEvent, MainUiEffect>(
    initialState = MainUiState()
) {
    init {
        observeCounter()
    }

    private fun observeCounter() {
        viewModelScope.launch {
            getCounterFlowUseCase().collect { counter ->
                setState { copy(count = counter.value, isLoading = false) }
            }
        }
    }

    override fun setEvent(event: MainUiEvent) {
        when (event) {
            is MainUiEvent.OnToastShown -> {
                showToastEffect(event.message)
            }

            is MainUiEvent.OnDecrementClicked -> {
                decreaseCount()
            }

            is MainUiEvent.OnIncrementClicked -> {
                increaseCount()
            }
        }
    }

    private fun increaseCount() {
        viewModelScope.launch {
            setState { copy(isLoading = true) }
            try {
                increaseCounterUseCase()
            } catch (e: Exception) {
                setState { copy(isLoading = false) }
                setEffect { MainUiEffect.ShowToast("에러: ${e.message}") }
            }
        }
    }

    private fun decreaseCount() {
        viewModelScope.launch {
            setState { copy(isLoading = true) }
            try {
                decreaseCounterUseCase()
            } catch (e: Exception) {
                setState { copy(isLoading = false) }
                setEffect { MainUiEffect.ShowToast("에러: ${e.message}") }
            }
        }
    }

    private fun showToastEffect(message: String) {
        setEffect { MainUiEffect.ShowToast(message) }
    }
}