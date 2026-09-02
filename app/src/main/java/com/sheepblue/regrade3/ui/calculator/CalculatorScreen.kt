package com.sheepblue.regrade3.ui.calculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sheepblue.regrade3.domain.RuleOfThreeCalculator
import com.sheepblue.regrade3.domain.model.CalculationType
import com.sheepblue.regrade3.domain.model.RuleOfThree
import com.sheepblue.regrade3.ui.calculator.components.CalculateButton
import com.sheepblue.regrade3.ui.calculator.components.CalculationTypeSelector
import com.sheepblue.regrade3.ui.calculator.components.CalculatorTable
import com.sheepblue.regrade3.ui.calculator.components.ResultCard
import com.sheepblue.regrade3.utils.isValidNumber


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

    var selectedType by remember { mutableStateOf(CalculationType.INVERSE) }

    var result by remember { mutableDoubleStateOf(0.0) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Column {
            CalculatorTable(
                numA = numA,
                numB = numB,
                numC = numC,
                onNumAChange = { if (isValidNumber(it)) numA = it },
                onNumBChange = { if (isValidNumber(it)) numB = it },
                onNumCChange = { if (isValidNumber(it)) numC = it }
            )
            Spacer(modifier = Modifier.height(3.dp))
            CalculationTypeSelector(
                selectedType = selectedType,
                options = CalculationType.entries,
                onClick = { selectedType = it }
            )
        }
        // TODO: ao clicar no botão VALIDAR os inputs, e então se possivel exibir o Card de Resultado
        CalculateButton {
            result = RuleOfThreeCalculator().calculate(
                RuleOfThree(
                    valueA = numA.toDouble(),
                    valueB = numB.toDouble(),
                    valueC = numC.toDouble(),
                    type = selectedType
                )
            )
        }

        //if (hasResult) {
        ResultCard(
            type = selectedType,
            result = result,
            numA = numA,
            numB = numB,
            numC = numC
        )
        // }
    }
}

@Composable
@Preview(showBackground = true)
fun CalculatorScreenPreview() {
    CalculatorScreen()
}
