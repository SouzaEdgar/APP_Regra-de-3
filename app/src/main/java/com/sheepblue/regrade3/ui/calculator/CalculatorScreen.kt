package com.sheepblue.regrade3.ui.calculator

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sheepblue.regrade3.ui.calculator.components.NumberInput
import com.sheepblue.regrade3.utils.isValidNumber

@Composable
fun CalculatorScreen() {
    var numA by rememberSaveable { mutableStateOf("") }
    var numB by rememberSaveable { mutableStateOf("") }
    var numC by rememberSaveable { mutableStateOf("") }

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
                onTextChange = {
                    if (isValidNumber(text = it)) numA = it
                },
                readOnly = false
            )
            // Segundo quadrante
            NumberInput(
                modifier = Modifier.weight(1f),
                quadrant = "B",
                text = numB,
                onTextChange = {
                    if (isValidNumber(text = it)) numB = it
                },
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
                onTextChange = {
                    if (isValidNumber(text = it)) numC = it
                },
                readOnly = false
            )
            // Quarto quadrante ( X )
            NumberInput(
                modifier = Modifier.weight(1f),
                quadrant = "X",
                text = numC,
                onTextChange = {
                    if (isValidNumber(text = it)) numC = it
                },
                readOnly = true
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CalculatorScreenPreview() {
    CalculatorScreen()
}
