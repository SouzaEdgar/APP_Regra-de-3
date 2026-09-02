package com.sheepblue.regrade3.ui.calculator.state

import com.sheepblue.regrade3.domain.enums.CalculationType
import com.sheepblue.regrade3.domain.enums.InputError
import com.sheepblue.regrade3.domain.model.CalculationResult

data class CalculatorUiState(
    val numA: String = "",
    val numB: String = "",
    val numC: String = "",
    val selectedType: CalculationType = CalculationType.DIRECT,
    val calculationResult: CalculationResult? = null,
    val wrongInput: List<InputError> = emptyList()
)
