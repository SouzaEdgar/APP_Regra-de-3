package com.sheepblue.regrade3.ui.calculator

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.sheepblue.regrade3.domain.RuleOfThreeCalculator
import com.sheepblue.regrade3.domain.enums.CalculationType
import com.sheepblue.regrade3.domain.enums.InputError
import com.sheepblue.regrade3.domain.model.CalculationResult
import com.sheepblue.regrade3.domain.model.RuleOfThree
import com.sheepblue.regrade3.ui.calculator.components.CalculateButton
import com.sheepblue.regrade3.ui.calculator.components.CalculationTypeSelector
import com.sheepblue.regrade3.ui.calculator.components.CalculatorTable
import com.sheepblue.regrade3.ui.calculator.components.ResultCard
import com.sheepblue.regrade3.ui.calculator.viewmodel.CalculatorViewModel
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
fun CalculatorScreen(
    viewModel: CalculatorViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    //var numA by rememberSaveable { mutableStateOf("") }
    //var numB by rememberSaveable { mutableStateOf("") }
    //var numC by rememberSaveable { mutableStateOf("") }

    //var selectedType by remember { mutableStateOf(CalculationType.DIRECT) }

    var showCard by remember { mutableStateOf(false) }

    //var calculationResult by remember { mutableStateOf<CalculationResult?>(null) }

    //val wrongInput = remember { mutableStateListOf<InputError>() }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Column {
            CalculatorTable(
                numA = uiState.numA,
                numB = uiState.numB,
                numC = uiState.numC,
                onNumAChange = {
                    showCard = false
                    if (isValidNumber(it)) viewModel.onNumAChange(it)
               },
                onNumBChange = {
                    showCard = false
                    if (isValidNumber(it)) viewModel.onNumBChange(it)
                },
                onNumCChange = {
                    showCard = false
                    if (isValidNumber(it)) viewModel.onNumCChange(it)
                },
                wrongInput = uiState.wrongInput
            )
            Spacer(modifier = Modifier.height(3.dp))
            CalculationTypeSelector(
                selectedType = uiState.selectedType,
                options = CalculationType.entries,
                onClick = {
                    showCard = false
                    viewModel.onTypeSelected(it)
                }
            )
        }
        // TODO: mover controle de estado e calculo para ViewModel
        CalculateButton {
            viewModel.onCalculateClick()
        }

        when(val result = uiState.calculationResult) {
            is CalculationResult.Success -> {
                AnimatedVisibility(visible = true) {
                    ResultCard(
                        result = result.result
                    )
                }
            }
            is CalculationResult.Error -> {}
            else -> {}
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CalculatorScreenPreview() {
    CalculatorScreen(
        viewModel = CalculatorViewModel()
    )
}
