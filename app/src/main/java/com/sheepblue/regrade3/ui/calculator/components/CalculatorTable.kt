package com.sheepblue.regrade3.ui.calculator.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
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
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Primeiro quadrante
            NumberInput(
                modifier = Modifier.weight(1f),
                quadrant = "A",
                text = numA,
                onTextChange = onNumAChange,
                readOnly = false,
                isError = InputError.VALUE_A in wrongInput
            )

            // Segundo quadrante
            NumberInput(
                modifier = Modifier.weight(1f),
                quadrant = "B",
                text = numB,
                onTextChange = onNumBChange,
                readOnly = false,
                isError = InputError.VALUE_B in wrongInput
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Terceiro quadrante
            NumberInput(
                modifier = Modifier.weight(1f),
                quadrant = "C",
                text = numC,
                onTextChange = onNumCChange,
                readOnly = false,
                isError = InputError.VALUE_C in wrongInput
            )

            // Quarto quadrante ( X )
            NumberInput(
                modifier = Modifier.weight(1f),
                quadrant = "X",
                text = "???",
                onTextChange = {},
                readOnly = true,
                isError = false
            )
        }
    }
}
