package com.sheepblue.regrade3.domain.model

import com.sheepblue.regrade3.domain.enums.CalculationType

data class RuleOfThree (
    val valueA: Double,
    val valueB: Double,
    val valueC: Double,
    val type: CalculationType
)
