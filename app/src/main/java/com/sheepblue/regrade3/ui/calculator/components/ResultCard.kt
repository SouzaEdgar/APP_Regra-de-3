package com.sheepblue.regrade3.ui.calculator.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sheepblue.regrade3.ui.theme.RegraDe3Theme

// TODO: preparar o tipo de calculo (grandeza direta(cruzada) ou inversa(reta))
//   trocar o type: Bool por algo como type: CalculationFormula

@Composable
fun ResultCard(
    type: Boolean,
    result: Double,
    numA: String,
    numB: String,
    numC: String
) {
    val formulaNumerator  = if (type) "B * C" else "A * B"
    val formulaDenominator = if (type) "A" else "C"
    val numerator = if (type) "$numB * $numC" else "$numA * $numB"
    val denominator = if (type) numA else numC

    Card(
        modifier = Modifier.padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextStyled(text = "X = ")
            Fraction(Modifier.weight(1f), formulaNumerator, formulaDenominator)
            TextStyled(" => ")
            Fraction(Modifier.weight(1f), numerator, denominator)
            TextStyled(" = $result")
        }

    }
}

@Composable
fun TextStyled(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold
    )
}

@Composable
@Preview(name = "light", showBackground = true)
@Preview(name = "dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun ResultCardPreview() {
    RegraDe3Theme {
        Surface(color = MaterialTheme.colorScheme.background) {
            Box(modifier = Modifier.fillMaxSize()) {
                ResultCard(
                    result = 26.0,
                    type = true,
                    numA = "5000",
                    numB = "65",
                    numC = "2000"
                )
            }
        }
    }
}
