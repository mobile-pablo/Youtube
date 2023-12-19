package com.mobile.pablo.uicomponents.views.keyboard.ext

import com.mobile.pablo.uicomponents.views.keyboard.data.KeysDataSource
import com.mobile.pablo.uicomponents.views.keyboard.data.model.Key
import com.mobile.pablo.uicomponents.views.keyboard.utility.NumericUtilityKey
import com.mobile.pablo.uicomponents.views.keyboard.utility.TextUtilityKey
import com.mobile.pablo.uicomponents.views.keyboard.utility.UtilityKey

/**
 *  Copied from https://github.com/UmairKhalid786/ComposeTvKeyboard
 */
fun Key.isBackspace() = this is UtilityKey.Backspace || this is NumericUtilityKey.Backspace

fun Key.isUppercase() = this is UtilityKey.Uppercase

fun Key.isToggleKey() = KeysDataSource.toggleKeys.contains(this)

fun Key.isAction() = this is UtilityKey.ActionArrow

fun Key.isNumeric() = this is TextUtilityKey.Numeric

fun Key.isAbc() = this is TextUtilityKey.ABC

fun Key.isClear() = this is UtilityKey.Clear

fun Key.isSpecialCharacters() = this is TextUtilityKey.SpecialCharacters

fun Key.handleCaseMode(isUppercaseEnabled: Boolean) =
    if (isUppercaseEnabled)
        text.uppercase()
    else
        text.lowercase()
