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
import com.sheepblue.regrade3.utils.isValidNumber

@Composable
fun NumberInput(
    modifier: Modifier,
    quadrant: String,
    text: String,
    onTextChange: (String) -> Unit,
    readOnly: Boolean,
    isError: Boolean
) {
    OutlinedTextField(
        value = text,
        onValueChange = onTextChange,
        readOnly = readOnly,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
        label = { Text("Valor $quadrant") },
        placeholder = { Text("Digite o valor de $quadrant") },
        modifier = modifier,
        isError = isError
    )
}

@Composable
@Preview(showBackground = true)
fun NumberInputPreview() {
    Box(modifier = Modifier.fillMaxSize()) {
        var num by rememberSaveable { mutableStateOf("") }
        NumberInput(
            modifier = Modifier,
            quadrant = "A",
            text = num,
            onTextChange = { text ->
                if (isValidNumber(text)) {
                    num = text
                }
            },
            readOnly = true,
            isError = false
        )
    }
}
