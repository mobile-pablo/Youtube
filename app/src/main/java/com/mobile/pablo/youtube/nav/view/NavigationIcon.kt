package com.mobile.pablo.youtube.nav.view

import androidx.compose.material.Badge
import androidx.compose.material.BadgedBox
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import com.mobile.pablo.uicomponents.theme.tertiaryColor
import com.mobile.pablo.uicomponents.theme.tertiarySelectedColor
import com.mobile.pablo.youtube.nav.model.NavigationItem
import androidx.compose.material.MaterialTheme as Theme

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
                    Text(text = item.badgeCount.toString())
                }
            } else if (item.hasNews) {
                Badge(modifier = Modifier.semantics {
                    contentDescription = BADGE
                })
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