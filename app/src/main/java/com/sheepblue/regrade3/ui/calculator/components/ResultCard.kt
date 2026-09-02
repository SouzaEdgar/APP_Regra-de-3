package com.sheepblue.regrade3.ui.calculator.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sheepblue.regrade3.domain.model.RuleOfThreeResult
import com.sheepblue.regrade3.ui.theme.RegraDe3Theme

@Composable
fun ResultCard(
    result: RuleOfThreeResult
) {
    Card(
        modifier = Modifier.padding(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            TextStyled(text = "X = ")
            Fraction(Modifier.weight(1f), result.formulaNumerator, result.formulaDenominator)
            TextStyled(" => ")
            Fraction(Modifier.weight(1f), result.expressionNumerator, result.expressionDenominator)
            TextStyled(" = ${result.result}")
        }

    }
}

@Composable
private fun TextStyled(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold
    )
}

@Composable
private fun Fraction(
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

@Composable
@Preview(name = "light", showBackground = true)
@Preview(name = "dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun ResultCardPreview() {
    RegraDe3Theme {
        Surface(color = MaterialTheme.colorScheme.background) {
            Box() {
                ResultCard(
                    result = RuleOfThreeResult(
                        result = 26.0,
                        formulaNumerator = "B * C",
                        formulaDenominator = "A",
                        expressionNumerator = "65 * 2000",
                        expressionDenominator = "5000"
                    )
                )
            }
        }
    }
}
