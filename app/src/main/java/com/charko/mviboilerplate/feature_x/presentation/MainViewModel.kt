package com.charko.mviboilerplate.feature_x.presentation

import androidx.lifecycle.viewModelScope
import com.charko.mviboilerplate.base.BaseViewModel
import com.charko.mviboilerplate.feature_x.domain.usecase.DecreaseCounterUseCase
import com.charko.mviboilerplate.feature_x.domain.usecase.GetCounterFlowUseCase
import com.charko.mviboilerplate.feature_x.domain.usecase.IncreaseCounterUseCase
import com.charko.mviboilerplate.feature_x.presentation.contact.MainUiEffect
import com.charko.mviboilerplate.feature_x.presentation.contact.MainUiEvent
import com.charko.mviboilerplate.feature_x.presentation.contact.MainUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCounterFlowUseCase: GetCounterFlowUseCase,
    private val increaseCounterUseCase: IncreaseCounterUseCase,
    private val decreaseCounterUseCase: DecreaseCounterUseCase,
) : BaseViewModel<MainUiState, MainUiEvent, MainUiEffect>(
    initialState = MainUiState()
) {

    override fun setEvent(event: MainUiEvent) {
        when (event) {
            is MainUiEvent.FetchCountInfo -> fetchCountInfo()
            is MainUiEvent.OnDecrementClicked -> decreaseCount()
            is MainUiEvent.OnIncrementClicked -> increaseCount()
            is MainUiEvent.OnToastShown -> showToastEffect(event.message)

        }
    }

    private fun fetchCountInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            setState { copy(isLoading = true) }

            try {
                getCounterFlowUseCase().collect { counter ->
                    setState { copy(count = counter.value) }
                }
            } catch (e: Exception) {
                setState { copy(isLoading = false) }
                setEffect { MainUiEffect.ShowToast("에러: ${e.message}") }
            }
        }
    }

    private fun increaseCount() {
        viewModelScope.launch(Dispatchers.IO) {
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
        viewModelScope.launch(Dispatchers.IO) {
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