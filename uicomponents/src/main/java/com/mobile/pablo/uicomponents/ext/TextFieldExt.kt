package com.mobile.pablo.uicomponents.ext

import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.input.TextFieldValue
import com.mobile.pablo.core.util.EMPTY_STRING

fun TextFieldValue.removeLastCharOrEmpty(): TextFieldValue {
    val length = text.length - 1
    return TextFieldValue(if (length >= 0) text.take(length) else "")
}

fun MutableState<TextFieldValue>.updateWith(text: String?) {
    value = TextFieldValue(text = text ?: return)
}

fun MutableState<Boolean>.toggle() {
    value = !value
}

fun MutableState<TextFieldValue>.clear() {
    updateWith(EMPTY_STRING)
}

fun MutableState<TextFieldValue>.updateAndRemoveLastChar() {
    updateWith(value.removeLastCharOrEmpty())
}

fun MutableState<TextFieldValue>.updateWith(value: TextFieldValue?) {
    updateWith(value?.text)
}

fun MutableState<TextFieldValue>.append(newText: String?) {
    updateWith(value.text.plus(newText))
}
