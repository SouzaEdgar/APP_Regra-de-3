package com.sheepblue.regrade3.domain

import com.sheepblue.regrade3.domain.enums.CalculationType
import com.sheepblue.regrade3.domain.model.RuleOfThree

class RuleOfThreeCalculator {
    fun calculate(ruleOfThree: RuleOfThree): Double {
        return when(ruleOfThree.type) {
            CalculationType.DIRECT -> calculateDirect(ruleOfThree)
            CalculationType.INVERSE -> calculateInverse(ruleOfThree)
        }
    }

    private fun calculateDirect(ruleOfThree: RuleOfThree): Double {
        return (ruleOfThree.valueB * ruleOfThree.valueC) / ruleOfThree.valueA
    }

    private fun calculateInverse(ruleOfThree: RuleOfThree): Double {
        return (ruleOfThree.valueA * ruleOfThree.valueB) / ruleOfThree.valueC
    }
}
