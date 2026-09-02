package com.sheepblue.regrade3.utils

import com.sheepblue.regrade3.domain.enums.InputError

fun isValidNumber(text: String): Boolean {
    var pointCount = 0

    for (char in text) {
        when {
            char == '.' -> pointCount ++
            !char.isDigit() -> return false
        }
    }
    return pointCount <= 1
}

fun validateInputs(
    valueA: String,
    valueB: String,
    valueC: String
): List<InputError> {
    val wrongInput = mutableListOf<InputError>()

    if (valueA.isEmpty()) wrongInput.add(InputError.VALUE_A)
    if (valueB.isEmpty()) wrongInput.add(InputError.VALUE_B)
    if (valueC.isEmpty()) wrongInput.add(InputError.VALUE_C)

    return wrongInput
}
