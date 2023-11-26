package com.mobile.pablo.error.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.tv.material3.Text
import com.mobile.pablo.error.R
import com.mobile.pablo.uicomponents.theme.MONTSERRAT_FONT_FAMILY
import com.mobile.pablo.uicomponents.theme.bodyTextColor
import com.mobile.pablo.uicomponents.theme.font
import com.mobile.pablo.uicomponents.theme.primaryColor
import com.mobile.pablo.uicomponents.theme.secondaryColor
import com.mobile.pablo.uicomponents.theme.spacing
import com.ramcosta.composedestinations.annotation.Destination
import androidx.compose.material.MaterialTheme as Theme

private const val ERROR_DESCRIPTION_MAX_LINES = 2

@Composable
@Destination
fun ErrorScreen(
    navController: NavController = rememberNavController()
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.colors.primaryColor)
            .padding(horizontal = Theme.spacing.spacing_82)
    ) {
        Box(
            contentAlignment = Alignment.BottomCenter
        ) {
            Icon(
                imageVector = Icons.Filled.Search,
                modifier = Modifier
                    .size(Theme.spacing.spacing_240)
                    .clickable {
                        navController.popBackStack()
                    },
                contentDescription = SEARCH_BADGE_TAG,
                tint = Theme.colors.secondaryColor
            )
            Text(
                fontFamily = MONTSERRAT_FONT_FAMILY,
                fontWeight = FontWeight.Normal,
                text = stringResource(id = R.string.missing_connection_button_text),
                fontSize = Theme.font.font_16,
                color = Theme.colors.bodyTextColor
            )
        }
        Column(modifier = Modifier.wrapContentSize()) {
            Text(
                fontFamily = MONTSERRAT_FONT_FAMILY,
                fontWeight = FontWeight.SemiBold,
                text = stringResource(id = R.string.missing_connection_title),
                fontSize = Theme.font.font_28,
                color = Theme.colors.bodyTextColor
            )
            Text(
                fontFamily = MONTSERRAT_FONT_FAMILY,
                text = stringResource(id = R.string.missing_connection_description),
                maxLines = ERROR_DESCRIPTION_MAX_LINES,
                fontSize = Theme.font.font_20,
                color = Theme.colors.bodyTextColor
            )
        }
    }
}

internal const val SEARCH_BADGE_TAG = "search_badge_tag"