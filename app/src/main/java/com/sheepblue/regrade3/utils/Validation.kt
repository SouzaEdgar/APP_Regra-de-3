package com.sheepblue.regrade3.utils

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
