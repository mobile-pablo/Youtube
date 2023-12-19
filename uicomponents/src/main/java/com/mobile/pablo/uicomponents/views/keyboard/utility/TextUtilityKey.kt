package com.mobile.pablo.uicomponents.views.keyboard.utility

import androidx.annotation.DrawableRes
import com.mobile.pablo.uicomponents.R
import com.mobile.pablo.uicomponents.views.keyboard.constants.KeysConstants

/**
 *  Copied from https://github.com/UmairKhalid786/ComposeTvKeyboard
 */
sealed class TextUtilityKey(
    @DrawableRes override val iconId: Int,
    override val text: String,
    override val span: Int = 1
) : UtilityKey(iconId, text, span) {

    data object Numeric : TextUtilityKey(
        R.drawable.ic_123_24,
        KeysConstants.NUMERIC_KEY
    )

    data object SpecialCharacters : TextUtilityKey(
        R.drawable.ic_numbers_24,
        KeysConstants.SPECIAL_CHARACTERS_KEY
    )

    data object ABC : TextUtilityKey(
        R.drawable.ic_abc_24,
        KeysConstants.ABC_KEY
    )
}
