package com.sheepblue.regrade3.domain.model

import com.sheepblue.regrade3.domain.enums.CalculationType
import java.math.BigDecimal

data class RuleOfThree (
    val valueA: BigDecimal,
    val valueB: BigDecimal,
    val valueC: BigDecimal,
    val type: CalculationType
)
