package com.charko.mviboilerplate.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<State : UiState, Event : UiEvent, Effect : UiEffect>(initialState: State) : ViewModel() {

    private val _uiState = MutableStateFlow<State>(initialState)
    val uiState: StateFlow<State> = _uiState.asStateFlow()

    private val _uiEffect = Channel<Effect>()
    val uiEffect: Flow<Effect> = _uiEffect.receiveAsFlow()

    abstract fun setEvent(event: Event)

    protected fun setState(reducer: State.() -> State) {
        _uiState.update { it.reducer() }
    }

    protected fun setEffect(builder: () -> Effect) {
        val effectValue = builder()
        viewModelScope.launch {
            _uiEffect.send(effectValue)
        }
    }
}