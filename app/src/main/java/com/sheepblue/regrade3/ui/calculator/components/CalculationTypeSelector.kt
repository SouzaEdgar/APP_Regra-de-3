package com.sheepblue.regrade3.ui.calculator.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sheepblue.regrade3.domain.model.CalculationType

@Composable
fun CalculationTypeSelector(
    selectedType: CalculationType,
    options: List<CalculationType>,
    onClick: (CalculationType) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            text = "Tipo de cálculo",
            style = MaterialTheme.typography.labelLarge
        )
        Spacer(modifier = Modifier.width(8.dp))
        SingleChoiceSegmentedButtonRow {
            options.forEachIndexed { index, type ->
                SegmentedButton(
                    modifier = Modifier.height(36.dp),
                    shape = SegmentedButtonDefaults.itemShape(
                        index = index,
                        count = options.size
                    ),
                    onClick = {  onClick(type) },
                    selected = selectedType == type,
                    label = { Text(type.label) }
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CalculationTypeSelectorPreview() {
    var selectedType by remember { mutableStateOf(CalculationType.INVERSE) }

    CalculationTypeSelector(
        selectedType = selectedType,
        options = CalculationType.entries,
        onClick = { selectedType = it }
    )
}
