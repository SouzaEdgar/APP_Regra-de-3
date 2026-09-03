package com.sheepblue.regrade3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.sheepblue.regrade3.ui.calculator.CalculatorScreen
import com.sheepblue.regrade3.ui.theme.RegraDe3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RegraDe3Theme {
                CalculatorScreen()
            }
        }
    }
}
