package com.mobile.pablo.uicomponents.views.keyboard.utility

import androidx.annotation.DrawableRes
import com.mobile.pablo.uicomponents.R
import com.mobile.pablo.uicomponents.views.keyboard.constants.KeysConstants

/**
 *  Copied from https://github.com/UmairKhalid786/ComposeTvKeyboard
 */
sealed class NumericUtilityKey(
    @DrawableRes override val iconId: Int,
    override val text: String,
    override val span: Int = 1
) : UtilityKey(iconId, text, span) {

    data object Backspace : NumericUtilityKey(
        R.drawable.ic_keyboard_backspace_24,
        KeysConstants.BACK_SPACE_KEY
    )

    data object Space : NumericUtilityKey(
        R.drawable.ic_space_bar_24,
        KeysConstants.SPACE_KEY
    )

    data object RightArrow :
        NumericUtilityKey(
            R.drawable.ic_arrow_right_alt_24,
            KeysConstants.RIGHT_ARROW_KEY
        )
}
