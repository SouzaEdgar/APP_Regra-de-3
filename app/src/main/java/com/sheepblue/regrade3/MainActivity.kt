package com.sheepblue.regrade3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.getValue
import androidx.core.view.WindowCompat.getInsetsController
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sheepblue.regrade3.ui.calculator.CalculatorScreen
import com.sheepblue.regrade3.ui.theme.RegraDe3Theme
import com.sheepblue.regrade3.ui.theme.ThemeViewModel
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
            val themeViewModel: ThemeViewModel = hiltViewModel()
            val isDarkMode by themeViewModel.isDarkMode.collectAsStateWithLifecycle(
                initialValue = isSystemInDarkTheme()
            )
            RegraDe3Theme(darkTheme = isDarkMode) {
                CalculatorScreen() // a integração Compose + Hilt ja resolve a questao da viewModel
            }
        }
    }
}
