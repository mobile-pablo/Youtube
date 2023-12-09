package com.mobile.pablo.youtube.nav.view

import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import com.mobile.pablo.uicomponents.theme.MONTSERRAT_FONT_FAMILY
import com.mobile.pablo.uicomponents.theme.tertiaryColor
import com.mobile.pablo.uicomponents.theme.tertiarySelectedColor
import com.mobile.pablo.youtube.nav.model.NavigationItem
import androidx.compose.material.MaterialTheme as Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun NavigationIcon(
    item: NavigationItem,
    selected: Boolean
) {
    BadgedBox(
        badge = {
            if (item.badgeCount != null) {
                Badge(
                    modifier = Modifier.semantics {
                        contentDescription = BADGE
                    }
                ) {
                    Text(
                        fontFamily = MONTSERRAT_FONT_FAMILY,
                        text = item.badgeCount.toString()
                    )
                }
            } else if (item.hasNews) {
                Badge(
                    modifier = Modifier.semantics {
                        contentDescription = BADGE
                    }
                )
            }
        }
    ) {
        Icon(
            imageVector = if (selected) item.selectedIcon else item.unselectedIcon,
            contentDescription = item.title,
            tint = if (selected) Theme.colors.tertiarySelectedColor else Theme.colors.tertiaryColor
        )
    }
}

internal val BADGE = "Badge"
