package com.sheepblue.regrade3.ui.calculator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.sheepblue.regrade3.ui.calculator.components.CalculatorTable
import com.sheepblue.regrade3.utils.isValidNumber

@Composable
fun CalculatorScreen() {
    var numA by rememberSaveable { mutableStateOf("") }
    var numB by rememberSaveable { mutableStateOf("") }
    var numC by rememberSaveable { mutableStateOf("") }

    CalculatorTable(
        numA = numA,
        numB = numB,
        numC = numC,
        onNumAChange = { if (isValidNumber(it)) numA = it },
        onNumBChange = { if (isValidNumber(it)) numB = it },
        onNumCChange = { if (isValidNumber(it)) numB = it }
    )
}

@Composable
@Preview(showBackground = true)
fun CalculatorScreenPreview() {
    CalculatorScreen()
}
