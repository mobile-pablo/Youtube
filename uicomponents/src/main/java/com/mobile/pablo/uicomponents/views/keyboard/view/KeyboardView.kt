package com.mobile.pablo.uicomponents.views.keyboard.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.mobile.pablo.uicomponents.ext.append
import com.mobile.pablo.uicomponents.ext.clear
import com.mobile.pablo.uicomponents.ext.toggle
import com.mobile.pablo.uicomponents.ext.updateAndRemoveLastChar
import com.mobile.pablo.uicomponents.theme.primaryColor
import com.mobile.pablo.uicomponents.theme.spacing
import com.mobile.pablo.uicomponents.views.keyboard.data.KeysDataSource
import com.mobile.pablo.uicomponents.views.keyboard.data.model.Key
import com.mobile.pablo.uicomponents.views.keyboard.ext.handleCaseMode
import com.mobile.pablo.uicomponents.views.keyboard.ext.isAbc
import com.mobile.pablo.uicomponents.views.keyboard.ext.isAction
import com.mobile.pablo.uicomponents.views.keyboard.ext.isBackspace
import com.mobile.pablo.uicomponents.views.keyboard.ext.isClear
import com.mobile.pablo.uicomponents.views.keyboard.ext.isNumeric
import com.mobile.pablo.uicomponents.views.keyboard.ext.isSpecialCharacters
import com.mobile.pablo.uicomponents.views.keyboard.ext.isToggleKey
import com.mobile.pablo.uicomponents.views.keyboard.ext.isUppercase
import androidx.compose.material.MaterialTheme as Theme

/**
 *  Copied from https://github.com/UmairKhalid786/ComposeTvKeyboard
 */
@Composable
fun KeyboardView(
    modifier: Modifier = Modifier,
    textFieldState: MutableState<TextFieldValue>?,
    focusFirstKey: Boolean = false,
    onAction: ((key: Key) -> Unit)? = null,
    onKeyPress: (key: Key) -> Unit
) {
    val focusKey = remember { mutableStateOf(focusFirstKey) }
    val isUppercase = remember { mutableStateOf(false) }
    val isNumeric = remember { mutableStateOf(false) }
    val isSpecialCharacters = remember { mutableStateOf(false) }
    val alphabets = remember { mutableStateOf(KeysDataSource.normalKeys) }
    val numericKeys = remember { mutableStateOf(KeysDataSource.numericKeys) }
    val specialCharactersKeys = remember { mutableStateOf(KeysDataSource.specialCharactersKeys) }

    val keys by rememberUpdatedState(
        if (isNumeric.value) {
            numericKeys.value
        } else if (isSpecialCharacters.value) {
            specialCharactersKeys.value
        } else {
            alphabets.value
        }
    )
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Theme.colors.primaryColor)
            .padding(Theme.spacing.spacing_16)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(5),
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = Theme.spacing.spacing_8)
        ) {
            items(
                keys.size,
                key = { index ->
                    keys[index].text
                },
                span = { index ->
                    GridItemSpan(keys[index].span)
                }
            ) { index ->
                KeyboardButton(
                    key = keys[index],
                    requestFocus = focusKey.value && index == 0,
                    isUppercaseEnable = isUppercase.value,
                    isToggle = keys[index].isToggleKey()
                ) {
                    if (it.isUppercase()) {
                        isUppercase.toggle()
                    } else if (it.isAction()) {
                        onAction?.invoke(it)
                    } else if (it.isSpecialCharacters()) {
                        isSpecialCharacters.toggle()
                        isNumeric.value = false
                    } else if (it.isNumeric() || it.isAbc()) {
                        isNumeric.toggle()
                        isSpecialCharacters.value = false
                    } else {
                        onKeyPress(it)
                        processKeys(it, textFieldState, isUppercase.value)
                    }
                }
            }
        }
    }
}

fun processKeys(
    it: Key,
    state: MutableState<TextFieldValue>?,
    isUppercase: Boolean
) {
    if (it.isBackspace()) {
        state?.updateAndRemoveLastChar()
    } else if (it.isClear()) {
        state?.clear()
    } else {
        state?.append(it.handleCaseMode(isUppercase))
    }
}

@Preview
@Composable
fun KeyboardViewPreview() {
    KeyboardView(textFieldState = null) {}
}
