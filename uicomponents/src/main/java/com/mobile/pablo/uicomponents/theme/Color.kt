package com.mobile.pablo.uicomponents.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.mobile.pablo.uicomponents.theme.customColors.DayColors as Day
import com.mobile.pablo.uicomponents.theme.customColors.NightColors as Night

/**
 *  Extension properties for [Colors]
 *  @property primaryColor [Color] is
 *  the main color that occupies 60% of the color scheme.
 *  It's the dominant color in application.
 */
val Colors.primaryColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Night.primaryColor else Day.primaryColor

/**
 *  Extension properties for [Colors]
 *  @property secondaryColor [Color] is
 *  the secondary color that occupies 30% of the color scheme.
 */
val Colors.secondaryColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Night.secondaryColor else Day.secondaryColor

/**
 *  Extension properties for [Colors]
 *  @property tertiaryColor [Color] is
 *  the secondary color that occupies 10% of the color scheme.
 */
val Colors.tertiaryColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Night.tertiaryColor else Day.tertiaryColor

/**
 *  Extension properties for [Colors]
 *  @property tertiarySelectedColor [Color] is
 *  the secondary color that is currently selected occupies 10% of the color scheme.
 *  Example usage : Selected icon or button.
 */
val Colors.tertiarySelectedColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Night.tertiarySelectedColor else Day.tertiarySelectedColor

/**
 *  Extension properties for [Colors]
 *  @property bodyTextColor [Color] is
 *  Color of common text used in application.
 */
val Colors.bodyTextColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Night.bodyTextColor else Day.bodyTextColor

/**
 *  Extension properties for [Colors]
 *  @property progressColor [Color] is
 *  Color of progress bar background used in application.
 */
val Colors.progressColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Night.progressColor else Day.progressColor

/**
 *  Extension properties for [Colors]
 *  @property progressBackgroundColor [Color] is
 *  Color of progress bar background used in application.
 */
val Colors.progressBackgroundColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Night.progressBackgroundColor else Day.progressBackgroundColor