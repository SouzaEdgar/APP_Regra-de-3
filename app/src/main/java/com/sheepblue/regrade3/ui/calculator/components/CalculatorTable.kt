package com.sheepblue.regrade3.ui.calculator.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sheepblue.regrade3.domain.enums.InputError

@Composable
fun CalculatorTable(
    numA: String,
    numB: String,
    numC: String,
    onNumAChange: (String) -> Unit,
    onNumBChange: (String) -> Unit,
    onNumCChange: (String) -> Unit,
    wrongInput: List<InputError>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, bottom = 8.dp, end = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Primeiro quadrante
        NumberInput(
            modifier = Modifier,
            quadrant = "A",
            text = numA,
            onTextChange = onNumAChange,
            readOnly = false,
            isError = InputError.VALUE_A in wrongInput
        )

        // Segundo quadrante
        NumberInput(
            modifier = Modifier,
            quadrant = "B",
            text = numB,
            onTextChange = onNumBChange,
            readOnly = false,
            isError = InputError.VALUE_B in wrongInput
        )

        // Terceiro quadrante
        NumberInput(
            modifier = Modifier,
            quadrant = "C",
            text = numC,
            onTextChange = onNumCChange,
            readOnly = false,
            isError = InputError.VALUE_C in wrongInput
        )
    }
}
