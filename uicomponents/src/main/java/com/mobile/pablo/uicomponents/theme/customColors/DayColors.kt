package com.mobile.pablo.uicomponents.theme.customColors

import androidx.compose.ui.graphics.Color

object DayColors : CustomColors {
    override val primaryColor: Color
        get() = Color(0xFFDAD7CD)

    override val secondaryColor: Color
        get() = Color(0xFFBEC8AD)

    override val secondarySelectedColor: Color
        get() = Color(0XFFABB49B)

    override val tertiaryColor: Color
        get() = Color(0xFF3A5A40)

    override val tertiarySelectedColor: Color
        get() = Color(0xFF344E41)

    override val bodyTextColor: Color
        get() = Color(0xFF282828)

    override val progressColor: Color
        get() = tertiaryColor

    override val progressBackgroundColor: Color
        get() = Color.LightGray
}
