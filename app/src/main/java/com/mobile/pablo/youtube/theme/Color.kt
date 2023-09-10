package com.mobile.pablo.youtube.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 *  In this project I am implementing design via
 *   60 - 30 - 10
 *       rule
 */

// Day
val PrimaryColorDay = Color(0xfffe0c22)
val SecondaryColorDay = Color(0xffffffff)
val TertiaryColorDay = Color(0xffb70918)

val PrimaryTextColorDay = Color(0xff000000)

// Night
val PrimaryColorNight = Color(0xff990000)
val SecondaryColorNight = Color(0xff342f3a)
val TertiaryColorNight = Color(0xffb70918)

val PrimaryTextColorNight = Color(0xffdddddd)

val Colors.primaryColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) PrimaryColorNight else PrimaryColorDay

val Colors.secondaryColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) SecondaryColorNight else SecondaryColorDay

val Colors.tertiaryColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) TertiaryColorNight else TertiaryColorDay

val Colors.primaryTextColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) PrimaryTextColorNight else PrimaryTextColorDay
