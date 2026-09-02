package com.sheepblue.regrade3.ui.calculator

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sheepblue.regrade3.domain.RuleOfThreeCalculator
import com.sheepblue.regrade3.domain.enums.CalculationType
import com.sheepblue.regrade3.domain.enums.InputError
import com.sheepblue.regrade3.domain.model.CalculationResult
import com.sheepblue.regrade3.domain.model.RuleOfThree
import com.sheepblue.regrade3.domain.model.RuleOfThreeResult
import com.sheepblue.regrade3.ui.calculator.components.CalculateButton
import com.sheepblue.regrade3.ui.calculator.components.CalculationTypeSelector
import com.sheepblue.regrade3.ui.calculator.components.CalculatorTable
import com.sheepblue.regrade3.ui.calculator.components.ResultCard
import com.sheepblue.regrade3.utils.isValidNumber
import com.sheepblue.regrade3.utils.validateInputs


/**
 * Mapa mental:
 *      Usuario abre tela
 *          \/
 *      Preenche valores
 *          \/
 *      Escolhe tipo
 *          \/
 *      Clica Clacular
 *          \/
 *      ViewModel calcula
 *          \/
 *      UiState recebe resultado
 *          \/
 *      ResultCard aparece
 */

@Composable
fun CalculatorScreen() {
    var numA by rememberSaveable { mutableStateOf("") }
    var numB by rememberSaveable { mutableStateOf("") }
    var numC by rememberSaveable { mutableStateOf("") }

    var selectedType by remember { mutableStateOf(CalculationType.DIRECT) }

    var showCard by remember { mutableStateOf(false) }

    var calculationResult by remember { mutableStateOf<CalculationResult?>(null) }

    val wrongInput = remember { mutableStateListOf<InputError>() }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Column {
            CalculatorTable(
                numA = numA,
                numB = numB,
                numC = numC,
                onNumAChange = {
                    showCard = false
                    wrongInput.remove(InputError.VALUE_A)
                    if (isValidNumber(it)) numA = it
               },
                onNumBChange = {
                    showCard = false
                    wrongInput.remove(InputError.VALUE_B)
                    if (isValidNumber(it)) numB = it
                },
                onNumCChange = {
                    showCard = false
                    wrongInput.remove(InputError.VALUE_C)
                    if (isValidNumber(it)) numC = it
                },
                wrongInput = wrongInput
            )
            Spacer(modifier = Modifier.height(3.dp))
            CalculationTypeSelector(
                selectedType = selectedType,
                options = CalculationType.entries,
                onClick = {
                    showCard = false
                    selectedType = it
                    wrongInput.clear()
                    calculationResult = null
                }
            )
        }
        // TODO: mover controle de estado e calculo para ViewModel
        CalculateButton {
            wrongInput.clear()
            val validate = validateInputs(numA, numB, numC)

            if (validate.isEmpty()) {
                calculationResult = RuleOfThreeCalculator().calculate(
                    RuleOfThree(
                        valueA = numA.toDouble(),
                        valueB = numB.toDouble(),
                        valueC = numC.toDouble(),
                        type = selectedType
                    )
                )

                when(val result = calculationResult) {
                    is CalculationResult.Success -> {
                        showCard = true
                    }

                    is CalculationResult.Error -> {
                        wrongInput.addAll(result.errors)
                    }

                    else -> {}
                }
            } else {
                wrongInput.addAll(validate)
            }
        }

        AnimatedVisibility(visible = showCard) {
            when(val result = calculationResult) {
                is CalculationResult.Success -> {
                    ResultCard(
                        result = result.result
                    )
                }
                else -> {}
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CalculatorScreenPreview() {
    CalculatorScreen()
}
