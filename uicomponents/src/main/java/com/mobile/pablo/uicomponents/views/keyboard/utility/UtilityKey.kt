package com.mobile.pablo.uicomponents.views.keyboard.utility

import androidx.annotation.DrawableRes
import com.mobile.pablo.uicomponents.R
import com.mobile.pablo.uicomponents.views.keyboard.constants.KeysConstants
import com.mobile.pablo.uicomponents.views.keyboard.data.model.Key

/**
 *  Copied from https://github.com/UmairKhalid786/ComposeTvKeyboard
 */
sealed class UtilityKey(
    @DrawableRes open val iconId: Int,
    override val text: String,
    override val span: Int = 1
) : Key {

    data object Uppercase : UtilityKey(
        R.drawable.ic_keyboard_capslock_24,
        KeysConstants.UPPER_CASE_KEY
    )

    data object Backspace : UtilityKey(
        R.drawable.ic_keyboard_backspace_24,
        KeysConstants.BACK_SPACE_KEY
    )

    data object Clear : UtilityKey(
        R.drawable.ic_delete_24,
        KeysConstants.CLEAR_KEY,
        2
    )

    data object Space : UtilityKey(
        R.drawable.ic_space_bar_24,
        KeysConstants.SPACE_KEY,
        3
    )

    data object ActionArrow : UtilityKey(
        R.drawable.ic_search_24,
        KeysConstants.SEARCH_KEY,
        2
    )
}
