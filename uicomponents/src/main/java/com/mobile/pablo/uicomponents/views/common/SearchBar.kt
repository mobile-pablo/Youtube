package com.mobile.pablo.uicomponents.views.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import com.mobile.pablo.uicomponents.theme.font
import com.mobile.pablo.uicomponents.theme.secondaryColor
import com.mobile.pablo.uicomponents.theme.spacing
import com.mobile.pablo.uicomponents.theme.tertiaryColor
import kotlinx.coroutines.delay
import androidx.compose.material.MaterialTheme as Theme

@Composable
fun SearchBar(
    hint: String,
    textFieldState: MutableState<TextFieldValue>,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = false,
    autoFocus: Boolean = false,
    height: Dp = Theme.spacing.spacing_56,
    elevation: Dp = Theme.spacing.spacing_4,
    cornerShape: Shape = RoundedCornerShape(Theme.spacing.spacing_16),
    backgroundColor: Color = Theme.colors.secondaryColor,
    onSearchClicked: (String) -> Unit = {},
    onTextChange: (String) -> Unit = {}
) {
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(autoFocus) {
        if (autoFocus) {
            delay(100)
            focusRequester.requestFocus()
        }
    }
    Row(
        modifier = modifier
            .height(height)
            .fillMaxWidth()
            .shadow(elevation = elevation, shape = cornerShape)
            .background(color = backgroundColor, shape = cornerShape),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicTextField(
            modifier = modifier
                .weight(5f)
                .fillMaxWidth()
                .padding(horizontal = Theme.spacing.spacing_12)
                .focusRequester(focusRequester)
                .onFocusChanged { focusState ->
                    if (focusState.isFocused) {
                        keyboardController?.show()
                    }
                },
            value = textFieldState.value,
            onValueChange = {
                textFieldState.value = it
                onTextChange(it.text)
            },
            enabled = isEnabled,
            textStyle = TextStyle(
                color = Theme.colors.tertiaryColor,
                fontSize = Theme.font.font_16,
                fontWeight = FontWeight.Bold
            ),
            decorationBox = { innerTextField ->
                if (textFieldState.value.text.isEmpty()) {
                    Text(
                        text = hint,
                        color = Theme.colors.tertiaryColor,
                        fontSize = Theme.font.font_16,
                        fontWeight = FontWeight.Bold
                    )
                }
                innerTextField()
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(onSearch = { onSearchClicked(textFieldState.value.text) }),
            singleLine = true
        )
    }
}
