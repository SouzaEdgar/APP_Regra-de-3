package com.sheepblue.regrade3.ui.calculator

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sheepblue.regrade3.domain.enums.CalculationType
import com.sheepblue.regrade3.domain.model.CalculationResult
import com.sheepblue.regrade3.domain.model.RuleOfThreeResult
import com.sheepblue.regrade3.ui.calculator.components.CalculateButton
import com.sheepblue.regrade3.ui.calculator.components.CalculationTypeSelector
import com.sheepblue.regrade3.ui.calculator.components.CalculatorTable
import com.sheepblue.regrade3.ui.calculator.components.ResultCard
import com.sheepblue.regrade3.ui.calculator.viewmodel.CalculatorViewModel
import com.sheepblue.regrade3.utils.isValidNumber


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorScreen(
    viewModel: CalculatorViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    // TODO: Corrige campo da tela, adiciona Scaffold
    //  melhora layout e espaçamento dos componentes, altera os tema
    //  MONTAR ESCOLHA DE TEMAS

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Regra de 3") },
                actions = {
                    IconButton(onClick = { /* tema */ }) {
                        Icon(
                            Icons.Default.DarkMode,
                            contentDescription = "botão de tema"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .widthIn(max = 340.dp)
                    .padding(top = 8.dp, start = 12.dp, end = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier
                        .padding(horizontal = 8.dp),
                    shape = RoundedCornerShape(20.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "ENTRADAS",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(horizontal = 8.dp)
                            )

                            Surface(
                                shape = RoundedCornerShape(16.dp),
                                color = MaterialTheme.colorScheme.surface,
                                tonalElevation = 2.dp,
                                modifier = Modifier.padding(horizontal = 8.dp)
                            ) {
                                CalculationTypeSelector(
                                    selectedType = uiState.selectedType,
                                    options = CalculationType.entries,
                                    onClick = { viewModel.onTypeSelected(it) }
                                )
                            }
                        }

                        Surface(
                            shape = RoundedCornerShape(16.dp),
                            color = MaterialTheme.colorScheme.surface,
                            tonalElevation = 2.dp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                        ) {
                            CalculatorTable(
                                numA = uiState.numA,
                                numB = uiState.numB,
                                numC = uiState.numC,
                                onNumAChange = {
                                    if (isValidNumber(it)) viewModel.onNumAChange(it)
                                },
                                onNumBChange = {
                                    if (isValidNumber(it)) viewModel.onNumBChange(it)
                                },
                                onNumCChange = {
                                    if (isValidNumber(it)) viewModel.onNumCChange(it)
                                },
                                wrongInput = uiState.wrongInput
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                CalculateButton {
                    viewModel.onCalculateClick()
                }
            }
            AnimatedVisibility(
//                visible = true,
                visible = uiState.calculationResult is CalculationResult.Success,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(start = 12.dp, end = 12.dp, bottom = 16.dp)
            ) {
//                ResultCard(
//                    result = RuleOfThreeResult(
//                        result = 26.0,
//                        formulaNumerator = "B * C",
//                        formulaDenominator = "A",
//                        expressionNumerator = "65 * 5000",
//                        expressionDenominator = "5000"
//                    )
//                )
                when(val result = uiState.calculationResult) {
                    is CalculationResult.Success -> {
                        ResultCard(result = result.result)
                    }
                    else -> {}
                }
            }
        }
    }
}


@Composable
@Preview(showBackground = true, showSystemUi = true)
fun CalculatorScreenPreview() {
    CalculatorScreen(
        viewModel = viewModel()
    )
}
