package com.mobile.pablo.uicomponents.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mobile.pablo.uicomponents.R

val LATO = FontFamily(
    listOf(
        Font(
            R.font.lato_regular,
            FontWeight.Normal
        ),
        Font(
            R.font.lato_black,
            FontWeight.Black
        ),
        Font(
            R.font.lato_bold,
            FontWeight.Bold
        ),
        Font(
            R.font.lato_thin,
            FontWeight.Thin
        ),
        Font(
            R.font.lato_light,
            FontWeight.Light
        )
    )
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = LATO,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)