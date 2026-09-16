package com.sheepblue.regrade3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import androidx.core.view.WindowCompat.getInsetsController
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.sheepblue.regrade3.ui.calculator.CalculatorScreen
import com.sheepblue.regrade3.ui.theme.RegraDe3Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint // para o hilt do compose funcionar | avisa o Hilt que essa tela recebe injeções
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val controller = getInsetsController(window, window.decorView)
        controller.hide(WindowInsetsCompat.Type.statusBars())
        controller.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

        setContent {
            RegraDe3Theme {
                CalculatorScreen() // a integração Compose + Hilt ja resolve a questao da viewModel
            }
        }
    }
}
