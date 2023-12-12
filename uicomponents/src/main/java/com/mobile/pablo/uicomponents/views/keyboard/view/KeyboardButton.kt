package com.mobile.pablo.uicomponents.views.keyboard.view

import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.mobile.pablo.uicomponents.ext.toggle
import com.mobile.pablo.uicomponents.theme.spacing
import com.mobile.pablo.uicomponents.views.keyboard.data.model.Digit
import com.mobile.pablo.uicomponents.views.keyboard.data.model.Key
import com.mobile.pablo.uicomponents.views.keyboard.ext.handleCaseMode
import com.mobile.pablo.uicomponents.views.keyboard.utility.TextUtilityKey
import com.mobile.pablo.uicomponents.views.keyboard.utility.UtilityKey
import kotlinx.coroutines.launch
import androidx.compose.material.MaterialTheme as Theme

/**
 *  Copied from https://github.com/UmairKhalid786/ComposeTvKeyboard
 */
@Composable
fun KeyboardButton(
    modifier: Modifier = Modifier,
    key: Key,
    requestFocus: Boolean,
    containerColor: Color = Theme.colors.primary,
    containerSelectedColor: Color = Theme.colors.primary,
    textColor: Color = Theme.colors.primary,
    textSelectedColor: Color = Theme.colors.primary,
    isUppercaseEnable: Boolean = false,
    isToggle: Boolean = false,
    wrapContent: Boolean = false,
    contentPadding: PaddingValues = PaddingValues(Theme.spacing.default),
    onClick: (key: Key) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val isToggleEnable = remember { mutableStateOf(isToggle) }
    val coroutineScope = rememberCoroutineScope()
    val focusRequester = remember { FocusRequester() }
    val conditionalModifier = remember {
        if (wrapContent)
            modifier
        else
            modifier.aspectRatio((key.span.toFloat() / 1F))
    }

    Button(
        onClick = {
            if (isToggle) isToggleEnable.toggle()

            onClick(key)
            coroutineScope.launch {
                focusRequester.requestFocus()
            }
        },
        contentPadding = contentPadding,
        colors = ButtonDefaults.buttonColors(
            containerColor =
                if (isFocused || isToggleEnable.value)
                    containerSelectedColor
                else
                    containerColor,
            contentColor =
                if (isFocused || isToggleEnable.value)
                    textSelectedColor
                else
                    textColor
        ),
        elevation = ButtonDefaults.buttonElevation(
            pressedElevation = Theme.spacing.default,
            defaultElevation = Theme.spacing.spacing_10,
            focusedElevation = Theme.spacing.spacing_32
        ),
        modifier = conditionalModifier
            .focusRequester(focusRequester)
            .focusable(interactionSource = interactionSource)
            .padding(Theme.spacing.spacing_4)
    ) {
        when (key) {
            is TextUtilityKey -> Text(text = key.text)
            is UtilityKey -> Icon(
                painterResource(id = key.iconId),
                contentDescription = key.text,
                modifier = Modifier.size(Theme.spacing.spacing_16)
            )

            else -> Text(
                text = key.handleCaseMode(isUppercaseEnable)
            )
        }
    }

    LaunchedEffect(key1 = Unit) {
        if (requestFocus) {
            focusRequester.requestFocus()
        }
    }
}

@Preview
@Composable
fun KeyboardButtonPreview() {
    KeyboardButton(key = Digit.Zero, requestFocus = false) {}
}
