package com.sheepblue.regrade3.ui.calculator.viewmodel

import androidx.lifecycle.ViewModel
import com.sheepblue.regrade3.ui.calculator.state.CalculatorUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CalculatorViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(CalculatorUiState())

    val uiState = _uiState.asStateFlow()
}
