package com.sheepblue.regrade3.ui.calculator.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sheepblue.regrade3.domain.model.RuleOfThreeResult
import com.sheepblue.regrade3.ui.theme.RegraDe3Theme
import com.sheepblue.regrade3.utils.toDisplayString
import java.math.BigDecimal

@Composable
fun ResultCard(
    result: RuleOfThreeResult
) {
    val formulaParts = result.formulaNumerator.toBinaryParts()

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            HeaderResult(result.result)

            FractionBlock(
                title = "Fórmula",
                left = formulaParts.first,
                right = formulaParts.second,
                denominator = result.formulaDenominator
            )

            FractionBlock(
                title = "Substituição",
                left = result.expressionNumerator[0].toDisplayString(),
                right = result.expressionNumerator[1].toDisplayString(),
                denominator = result.expressionDenominator.toDisplayString()
            )
        }
    }
}

@Composable
private fun HeaderResult(result: BigDecimal) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "X = ",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        // usar função para aplicar formatação no BigDecimal
        Text(
            text = result.toDisplayString(),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.ExtraBold,
            maxLines = 1,
            softWrap = false,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun FractionBlock(
    title: String,
    left: String,
    right: String,
    denominator: String
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 2.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold
            )

            Fraction(
                modifier = Modifier.fillMaxWidth(),
                left = left,
                right = right,
                denominator = denominator
            )
        }
    }
}

@Composable
private fun Fraction(
    modifier: Modifier,
    left: String,
    right: String,
    denominator: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = left,
                maxLines = 1,
                softWrap = false,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Medium
            )

            Text(
                text = " × ",
                modifier = Modifier.padding(horizontal = 6.dp),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = right,
                maxLines = 1,
                softWrap = false,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Medium
            )
        }

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 4.dp)
        )

        Text(
            text = denominator,
            maxLines = 1,
            softWrap = false,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Medium
        )
    }
}

private fun String.toBinaryParts(): Pair<String, String> {
    val parts = split(" * ", limit = 2)
    return parts.getOrElse(0) { "" } to parts.getOrElse(1) { "" }
}


@Composable
@Preview(name = "light", showBackground = true)
@Preview(name = "dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun ResultCardPreview() {
    RegraDe3Theme {
        Surface(color = MaterialTheme.colorScheme.background) {
            Box(modifier = Modifier.fillMaxWidth()) {
                ResultCard(
                    result = RuleOfThreeResult(
                        result = BigDecimal("1234567890.00001111111111111111"),
                        formulaNumerator = "B * C",
                        formulaDenominator = "A",
                        expressionNumerator = listOf(
                            BigDecimal("12345678901234567890"),
                            BigDecimal("12345678901234567890")
                        ),
                        expressionDenominator = BigDecimal("12345678901234567890")
                    )
                )
            }
        }
    }
}
