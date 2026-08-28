package com.sheepblue.regrade3.ui.calculator.components

import android.content.res.Configuration
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sheepblue.regrade3.ui.theme.RegraDe3Theme

@Composable
fun CalculateButton(
    onClick: () -> Unit
) {
    val pressButton = remember { MutableInteractionSource() }

    val pressed by pressButton.collectIsPressedAsState()

    val cornerRadius by animateDpAsState(
        targetValue = if (pressed) 4.dp else 24.dp
    )
    val shape = RoundedCornerShape(size = cornerRadius)

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ){
        OutlinedButton(
            onClick = { onClick() },
            interactionSource = pressButton,
            shape = shape
        ) {
            Text("CALCULAR")
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
