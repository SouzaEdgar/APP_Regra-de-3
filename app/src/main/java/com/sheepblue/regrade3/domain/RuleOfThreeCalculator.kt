package com.sheepblue.regrade3.domain

import com.sheepblue.regrade3.domain.enums.CalculationType
import com.sheepblue.regrade3.domain.enums.InputError
import com.sheepblue.regrade3.domain.model.CalculationResult
import com.sheepblue.regrade3.domain.model.RuleOfThree
import com.sheepblue.regrade3.domain.model.RuleOfThreeResult

class RuleOfThreeCalculator {
    fun calculate(rule: RuleOfThree): CalculationResult {

        if (rule.type == CalculationType.DIRECT) {
            if (rule.valueA == 0.0) return CalculationResult.Error(errors = listOf(InputError.VALUE_A))
        } else {
            if (rule.valueC == 0.0) return CalculationResult.Error(errors = listOf(InputError.VALUE_C))
        }

        when (rule.type) {
            CalculationType.DIRECT -> return CalculationResult.Success(
                result = RuleOfThreeResult(
                    result = calculateDirect(rule),
                    formulaNumerator = "B * C",
                    formulaDenominator = "A",
                    expressionNumerator = "${rule.valueB} * ${rule.valueC}",
                    expressionDenominator = "${rule.valueA}"
                )
            )
            CalculationType.INVERSE -> return CalculationResult.Success(
                result = RuleOfThreeResult(
                    result = calculateInverse(rule),
                    formulaNumerator = "A * B",
                    formulaDenominator = "C",
                    expressionNumerator = "${rule.valueA} * ${rule.valueB}",
                    expressionDenominator = "${rule.valueC}"
                )
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
