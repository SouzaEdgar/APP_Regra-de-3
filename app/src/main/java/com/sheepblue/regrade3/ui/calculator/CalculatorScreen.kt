package com.sheepblue.regrade3.ui.calculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sheepblue.regrade3.ui.calculator.components.CalculateButton
import com.sheepblue.regrade3.ui.calculator.components.CalculatorTable
import com.sheepblue.regrade3.ui.calculator.components.ResultCard
import com.sheepblue.regrade3.utils.isValidNumber

@Composable
fun CalculatorScreen() {
    var numA by rememberSaveable { mutableStateOf("") }
    var numB by rememberSaveable { mutableStateOf("") }
    var numC by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        CalculatorTable(
            numA = numA,
            numB = numB,
            numC = numC,
            onNumAChange = { if (isValidNumber(it)) numA = it },
            onNumBChange = { if (isValidNumber(it)) numB = it },
            onNumCChange = { if (isValidNumber(it)) numC = it }
        )
        // TODO: ao clicar no botão de calcular exibir o card ResultCard e somente nesse caso.
        CalculateButton {  }

        // TODO: trabalhar as variaveis que compoem o calculo do resultado
        ResultCard(
            type = true,
            result = 26.0,
            numA = numA,
            numB = numB,
            numC = numC
        )
    }
}

@Composable
@Preview(showBackground = true)
fun CalculatorScreenPreview() {
    CalculatorScreen()
}
