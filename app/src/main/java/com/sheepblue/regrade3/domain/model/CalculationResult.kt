package com.sheepblue.regrade3.domain.model

import com.sheepblue.regrade3.domain.enums.InputError

sealed class CalculationResult {
    data class Success(
        val result: RuleOfThreeResult
    ): CalculationResult()

    data class Error(
        val errors: List<InputError>
    ): CalculationResult()
}
