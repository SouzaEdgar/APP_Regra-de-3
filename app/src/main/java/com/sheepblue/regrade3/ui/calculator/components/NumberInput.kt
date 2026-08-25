package com.sheepblue.regrade3.ui.calculator.components


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun NumberInput(valor: String, text: String, onTextChange: (String) -> Unit) {
    OutlinedTextField(
        value = text,
        onValueChange = onTextChange,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
        label = { Text("Valor $valor") },
        placeholder = { Text("Digite o valor de $valor") },
    )
}

@Composable
@Preview(showBackground = true)
fun NumberInputPreview() {
    Box(modifier = Modifier.fillMaxSize()) {
        var num by rememberSaveable { mutableStateOf("") }
        NumberInput(
            valor = "A",
            text = num,
            onTextChange = { text ->
                if (
                    text.count { it == '.' } <= 1 &&
                    text.filter { it != '.' }.all(Char::isDigit)
                ) {
                    num = text
                }
            })
    }
}
