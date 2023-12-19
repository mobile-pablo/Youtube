package com.mobile.pablo.uicomponents.theme.customColors

import androidx.compose.ui.graphics.Color

object NightColors : CustomColors {
    override val primaryColor: Color
        get() = Color(0xFF202425) // 0xFF212224

    override val secondaryColor: Color
        get() = Color(0xFF1D2E28)

    override val secondarySelectedColor: Color
        get() = Color(0xFF3D5D51)

    override val tertiaryColor: Color
        get() = Color(0xFF066839)

    override val tertiarySelectedColor: Color
        get() = Color(0XFF0AB061)

    override val bodyTextColor: Color
        get() = Color(0xFFB6AFAA)

    override val progressColor: Color
        get() = Color(0xFFBEC8AD)

    override val progressBackgroundColor: Color
        get() = tertiaryColor
}
