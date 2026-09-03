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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sheepblue.regrade3.domain.enums.CalculationType
import com.sheepblue.regrade3.domain.model.CalculationResult
import com.sheepblue.regrade3.ui.calculator.components.CalculateButton
import com.sheepblue.regrade3.ui.calculator.components.CalculationTypeSelector
import com.sheepblue.regrade3.ui.calculator.components.CalculatorTable
import com.sheepblue.regrade3.ui.calculator.components.ResultCard
import com.sheepblue.regrade3.ui.calculator.viewmodel.CalculatorViewModel
import com.sheepblue.regrade3.utils.isValidNumber

@Composable
fun CalculatorScreen(
    viewModel: CalculatorViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

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
                    if (isValidNumber(it)) viewModel.onNumAChange(it)
               },
                onNumBChange = {
                    if (isValidNumber(it)) viewModel.onNumBChange(it)
                },
                onNumCChange = {
                    if (isValidNumber(it)) viewModel.onNumCChange(it)
                },
                wrongInput = uiState.wrongInput
            )

            Spacer(modifier = Modifier.height(3.dp))

            CalculationTypeSelector(
                selectedType = uiState.selectedType,
                options = CalculationType.entries,
                onClick = {
                    viewModel.onTypeSelected(it)
                }
            )
        }

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
