package com.sheepblue.regrade3.domain

import com.sheepblue.regrade3.domain.enums.CalculationType
import com.sheepblue.regrade3.domain.model.RuleOfThree
import com.sheepblue.regrade3.domain.model.RuleOfThreeResult

class RuleOfThreeCalculator {
    fun calculate(rule: RuleOfThree): RuleOfThreeResult {
        var hasResult = true

        if (rule.type == CalculationType.DIRECT) {
            if (rule.valueA == 0.0) hasResult = false
        } else {
            if (rule.valueC == 0.0) hasResult = false
        }

        when(rule.type) {
            CalculationType.DIRECT -> return RuleOfThreeResult(
                result = calculateDirect(rule),
                formulaNumerator = "B * C",
                formulaDenominator = "A",
                expressionNumerator = "${rule.valueB} * ${rule.valueC}",
                expressionDenominator = "${rule.valueA}",
                hasResult = hasResult
            )
            CalculationType.INVERSE -> return RuleOfThreeResult(
                result = calculateInverse(rule),
                formulaNumerator = "A * B",
                formulaDenominator = "C",
                expressionNumerator = "${rule.valueA} * ${rule.valueB}",
                expressionDenominator = "${rule.valueC}",
                hasResult = hasResult
            )
        }
    }

    private fun calculateDirect(ruleOfThree: RuleOfThree): Double {
        return (ruleOfThree.valueB * ruleOfThree.valueC) / ruleOfThree.valueA
    }

    private fun calculateInverse(ruleOfThree: RuleOfThree): Double {
        return (ruleOfThree.valueA * ruleOfThree.valueB) / ruleOfThree.valueC
    }
}
