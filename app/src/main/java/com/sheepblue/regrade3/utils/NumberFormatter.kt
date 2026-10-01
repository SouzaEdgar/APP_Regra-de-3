package com.sheepblue.regrade3.utils

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

fun BigDecimal.toDisplayString(): String {
    val normalized = stripTrailingZeros()

    val integerDigits = normalized.precision() - normalized.scale()

    if (integerDigits > 10 || integerDigits < -6) {
        val formatter = DecimalFormat(
            "0.######E0",
            DecimalFormatSymbols(Locale.US)
        ).apply {
            roundingMode = RoundingMode.HALF_UP
        }

        return formatter.format(normalized)
    }

    return normalized.toPlainString()
}
