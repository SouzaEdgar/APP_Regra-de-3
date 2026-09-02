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
    var result by remember { mutableDoubleStateOf(0.0) }

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
                }
            )
        }
        // TODO: refatorar validacao dos inputs antes do calculo
        CalculateButton {
            wrongInput.clear()
            val validate = validateInputs(numA, numB, numC)

            if (validate.isEmpty()) {
                result = RuleOfThreeCalculator().calculate(
                    RuleOfThree(
                        valueA = numA.toDouble(),
                        valueB = numB.toDouble(),
                        valueC = numC.toDouble(),
                        type = selectedType
                    )
                )
                showCard = true
            } else {
                validate.forEach { value ->
                    wrongInput.add(value)
                }
            }
        }

        AnimatedVisibility(visible = showCard) {
            ResultCard(
                type = selectedType,
                result = result,
                numA = numA,
                numB = numB,
                numC = numC
            )
        }

    }
}

// Cogitei deixar a verificação "validateInputs" dentro da função, porem, como eu iria
//   retornar depois a lista errorInputList (wrongInput), sendo que a função retorna
//   um RuleOfThreeResult, pensei em colocar na classe uma val de List<InputErro>, mas então
//   pensei "Isso realmente deveria pertencer a essa classe?" Então decidi deixar de fora e
//   com isso validar em outro lugar talvez
private fun onCalculateClick(
    numA: String,
    numB: String,
    numC: String,
    type: CalculationType
): RuleOfThreeResult {
    val rule = RuleOfThree(
        valueA = numA.toDouble(),
        valueB = numB.toDouble(),
        valueC = numC.toDouble(),
        type = type
    )

    var result = RuleOfThreeCalculator().calculate(rule)
    var formulaNume = ""
    var formulaDeno = ""
    var expressionNume = ""
    var expressionDeno = ""
    var hasResult = true

    if (type == CalculationType.DIRECT) {
        if (rule.valueA == 0.0) hasResult = false
    } else {
        if (rule.valueC == 0.0) hasResult = false
    }

    if (hasResult) {
        when(type) {
            CalculationType.DIRECT -> {
                formulaNume = "B * C"
                formulaDeno = "A"
                expressionNume = "${rule.valueB} * ${rule.valueC}"
                expressionDeno = "${rule.valueA}"
            }
            CalculationType.INVERSE -> {
                formulaNume = "A * B"
                formulaDeno = "C"
                expressionNume = "${rule.valueA} * ${rule.valueB}"
                expressionDeno = "${rule.valueC}"
            }
        }
    }

    return RuleOfThreeResult(
        result = result,
        formulaNumerator = formulaNume,
        formulaDenominator = formulaDeno,
        expressionNumerator = expressionNume,
        expressionDenominator = expressionDeno,
        hasResult = hasResult
    )
}


@Composable
@Preview(showBackground = true)
fun CalculatorScreenPreview() {
    CalculatorScreen()
}
