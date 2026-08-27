package com.sheepblue.regrade3.ui.calculator.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CalculatorTable(
    numA: String,
    numB: String,
    numC: String,
    onNumAChange: (String) -> Unit,
    onNumBChange: (String) -> Unit,
    onNumCChange: (String) -> Unit
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
                readOnly = false
            )
            // Segundo quadrante
            NumberInput(
                modifier = Modifier.weight(1f),
                quadrant = "B",
                text = numB,
                onTextChange = onNumBChange,
                readOnly = false
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
                readOnly = false
            )
            // Quarto quadrante ( X )
            NumberInput(
                modifier = Modifier.weight(1f),
                quadrant = "X",
                text = "???",
                onTextChange = {},
                readOnly = true
            )
        }
    }
}
