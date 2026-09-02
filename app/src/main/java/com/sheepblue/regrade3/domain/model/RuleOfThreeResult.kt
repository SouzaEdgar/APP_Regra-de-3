package com.sheepblue.regrade3.domain.model

data class RuleOfThreeResult(
    val result: Double,
    val formulaNumerator: String,
    val formulaDenominator: String,
    val expressionNumerator: String,
    val expressionDenominator: String
)
