package com.mobile.pablo.uicomponents.ext

import com.mobile.pablo.core.util.EMPTY_STRING

/**
 * Unfortunately couldn't make that function more clean.
 */
fun formatDuration(duration: String): String {
    var temp = duration.removePrefix("PT")
    val delimeter = ":"
    var hours = EMPTY_STRING
    var minutes = EMPTY_STRING
    var seconds = EMPTY_STRING

    if ("H" in temp) {
        val split = temp.split("H")
        hours = split[0]
        temp = split.getOrNull(1) ?: EMPTY_STRING
    }

    if ("M" in temp) {
        val split = temp.split("M")
        minutes = split[0]
        temp = split.getOrNull(1) ?: EMPTY_STRING
    }

    if ("S" in temp) {
        seconds = temp.removeSuffix("S")
    }

    if (minutes.length == 1) {
        minutes = "0$minutes"
    }

    if (seconds.length == 1) {
        seconds = "0$seconds"
    }

    hours = hours.takeIf { it.isNotEmpty() }?.plus(delimeter) ?: hours
    minutes = minutes.takeIf { it.isNotEmpty() }?.plus(delimeter) ?: minutes

    if (minutes.length > 2 && seconds.isEmpty()) {
        seconds = "00"
    }

    if (minutes.isEmpty() && hours.isEmpty() && seconds.isNotEmpty()) {
        minutes = "00:"
    }

    return "$hours$minutes$seconds"
}
