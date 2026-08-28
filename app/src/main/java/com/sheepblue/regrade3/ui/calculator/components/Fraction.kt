package com.sheepblue.regrade3.ui.calculator.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun Fraction(
    modifier: Modifier,
    numerator: String,
    denominator: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(numerator)
        HorizontalDivider()
        Text(denominator)
    }
}
