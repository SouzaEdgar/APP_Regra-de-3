package com.sheepblue.regrade3.ui.calculator.viewmodel

import androidx.lifecycle.ViewModel
import com.sheepblue.regrade3.domain.RuleOfThreeCalculator
import com.sheepblue.regrade3.domain.enums.CalculationType
import com.sheepblue.regrade3.domain.enums.InputError
import com.sheepblue.regrade3.domain.model.CalculationResult
import com.sheepblue.regrade3.domain.model.RuleOfThree
import com.sheepblue.regrade3.ui.calculator.state.CalculatorUiState
import com.sheepblue.regrade3.utils.validateInputs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(): ViewModel() {
    private val _uiState = MutableStateFlow(CalculatorUiState())

    val uiState = _uiState.asStateFlow()

    // CalculatorTable
    fun onNumAChange(value: String) {
        _uiState.update {
            it.copy(
                numA = value,
                wrongInput = it.wrongInput - InputError.VALUE_A
            )
        }
    }
    fun onNumBChange(value: String) {
        _uiState.update {
            it.copy(
                numB = value,
                wrongInput = it.wrongInput - InputError.VALUE_B
            )
        }
    }
    fun onNumCChange(value: String) {
        _uiState.update {
            it.copy(
                numC = value,
                wrongInput = it.wrongInput - InputError.VALUE_C
            )
        }
    }

    // CalculationTypeSelector
    fun onTypeSelected(type: CalculationType) {
        _uiState.update {
            it.copy(
                selectedType = type,
                calculationResult = null,
                wrongInput = emptyList()
            )
        }
    }

    // CalculateButton
    fun onCalculateClick() {
        val validate = validateInputs(
            valueA = _uiState.value.numA,
            valueB = _uiState.value.numB,
            valueC = _uiState.value.numC
        )

        if (validate.isEmpty()) {
            _uiState.update {
                it.copy(
                    wrongInput = emptyList(),
                    calculationResult = RuleOfThreeCalculator().calculate(
                        RuleOfThree(
                            valueA = it.numA.toDouble(),
                            valueB = it.numB.toDouble(),
                            valueC = it.numC.toDouble(),
                            type = it.selectedType
                        )
                    )
                )
            }
            when(val result = _uiState.value.calculationResult) {
                is CalculationResult.Success -> {}
                is CalculationResult.Error -> {
                    _uiState.update {
                        it.copy(wrongInput = it.wrongInput + (result.errors))
                    }
                }
                else -> {}
            }
        } else {
            _uiState.update {
                it.copy(wrongInput = it.wrongInput + validate)
            }
        }
    }
}
