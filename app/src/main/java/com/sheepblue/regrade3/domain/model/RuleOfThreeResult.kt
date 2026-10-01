package com.sheepblue.regrade3.domain.model

import java.math.BigDecimal

data class RuleOfThreeResult(
    val result: BigDecimal,
    val formulaNumerator: String,
    val formulaDenominator: String,
    val expressionNumerator: List<BigDecimal>,
    val expressionDenominator: BigDecimal
)
