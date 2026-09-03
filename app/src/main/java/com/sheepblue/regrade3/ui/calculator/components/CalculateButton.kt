package com.sheepblue.regrade3.ui.calculator.components

import android.content.res.Configuration
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sheepblue.regrade3.ui.theme.RegraDe3Theme
import kotlinx.coroutines.delay

@Composable
fun CalculateButton(
    onClick: () -> Unit
) {
    var clicked by remember { mutableStateOf(false) }

    val shapeRadius by animateDpAsState(
        targetValue = if (clicked) 4.dp else 24.dp
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        OutlinedButton(
            onClick = {
                clicked = true
                onClick()
            },
            shape = RoundedCornerShape(size = shapeRadius)
        ) {
            Text("CALCULAR")
        }
    }

    LaunchedEffect(clicked) {
        if (clicked) {
            delay(timeMillis = 300)
            clicked = false
        }
    }
}


@Composable
@Preview(name = "light", showBackground = true)
@Preview(name = "dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun CalculateButtonPreview() {
    RegraDe3Theme{
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                CalculateButton {}
            }
        }
    }
}
