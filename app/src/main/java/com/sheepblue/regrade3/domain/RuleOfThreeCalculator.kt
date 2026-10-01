package com.sheepblue.regrade3.domain

import com.sheepblue.regrade3.domain.enums.CalculationType
import com.sheepblue.regrade3.domain.enums.InputError
import com.sheepblue.regrade3.domain.model.CalculationResult
import com.sheepblue.regrade3.domain.model.RuleOfThree
import com.sheepblue.regrade3.domain.model.RuleOfThreeResult
import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode

class RuleOfThreeCalculator {
    // configurar o resultado da divisão
    private val mathContext = MathContext(20, RoundingMode.HALF_UP)

    fun calculate(rule: RuleOfThree): CalculationResult {
        if (rule.type == CalculationType.DIRECT) {
            if (rule.valueA == BigDecimal.ZERO) return CalculationResult.Error(errors = listOf(InputError.VALUE_A))
        } else {
            if (rule.valueC == BigDecimal.ZERO) return CalculationResult.Error(errors = listOf(InputError.VALUE_C))
        }

        when (rule.type) {
            CalculationType.DIRECT -> return CalculationResult.Success(
                result = RuleOfThreeResult(
                    result = calculateDirect(rule),
                    formulaNumerator = "B * C",
                    formulaDenominator = "A",
                    expressionNumerator = listOf(rule.valueB, rule.valueC),
                    expressionDenominator = rule.valueA
                )
            )
            CalculationType.INVERSE -> return CalculationResult.Success(
                result = RuleOfThreeResult(
                    result = calculateInverse(rule),
                    formulaNumerator = "A * B",
                    formulaDenominator = "C",
                    expressionNumerator = listOf(rule.valueA, rule.valueB),
                    expressionDenominator = rule.valueC
                )
            )
        }
    }

    private fun calculateDirect(ruleOfThree: RuleOfThree): BigDecimal {
        return ruleOfThree.valueB
            .multiply(ruleOfThree.valueC)
            .divide(ruleOfThree.valueA,mathContext)
    }

    private fun calculateInverse(ruleOfThree: RuleOfThree): BigDecimal {
        return ruleOfThree.valueA
            .multiply(ruleOfThree.valueB)
            .divide(ruleOfThree.valueC,mathContext)
    }
}
