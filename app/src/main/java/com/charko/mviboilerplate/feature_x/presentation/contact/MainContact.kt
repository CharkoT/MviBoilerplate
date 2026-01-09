package com.charko.mviboilerplate.feature_x.presentation.contact

import com.charko.mviboilerplate.base.UiEffect
import com.charko.mviboilerplate.base.UiEvent
import com.charko.mviboilerplate.base.UiState

data class MainUiState(
    val isLoading: Boolean = false,
    val count: Int = 0,
    val errorMessage: String? = null,
) : UiState

sealed class MainUiEvent: UiEvent {
    object FetchCountInfo: MainUiEvent()
    object OnIncrementClicked: MainUiEvent()
    object OnDecrementClicked: MainUiEvent()
    data class OnToastShown(val message: String): MainUiEvent()
}

sealed class MainUiEffect: UiEffect {
    data class ShowToast(val message: String): MainUiEffect()
}