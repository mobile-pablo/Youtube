package com.mobile.pablo.youtube.nav.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.NavigationRail
import androidx.compose.material.NavigationRailItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.util.fastForEachIndexed
import androidx.navigation.NavController
import com.mobile.pablo.uicomponents.ext.testTag
import com.mobile.pablo.uicomponents.theme.secondaryColor
import com.mobile.pablo.uicomponents.theme.spacing
import com.mobile.pablo.uicomponents.theme.tertiaryColor
import com.mobile.pablo.youtube.R
import com.mobile.pablo.youtube.nav.ext.navigateTo
import com.mobile.pablo.youtube.nav.graph.NavGraphs
import com.mobile.pablo.youtube.nav.model.NavigationItem
import androidx.compose.material.MaterialTheme as Theme

typealias OnNavigationItemSelected = (Int) -> Unit

@Composable
internal fun NavigationSideBar(
    modifier: Modifier = Modifier,
    items: List<NavigationItem>,
    selectedItemIndex: Int,
    navController: NavController? = null,
    onItemSelected: OnNavigationItemSelected
) {
    NavigationRail(
        backgroundColor = Theme.colors.secondaryColor,
        modifier = modifier,
        header = {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_youtube_34),
                    tint = Theme.colors.tertiaryColor,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(Theme.spacing.spacing_12)
                        .size(Theme.spacing.spacing_48)
                )
            }
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .testTag(R.string.side_bar_item_column),
                verticalArrangement = Arrangement.spacedBy(Theme.spacing.spacing_12, Alignment.Bottom)
            ) {
                items.fastForEachIndexed { index, item ->
                    NavigationRailItem(
                        modifier = Modifier.size(Theme.spacing.spacing_72),
                        selected = selectedItemIndex == index,
                        onClick = {
                            onItemSelected(index)
                            item.direction?.let { navController!!.navigateTo(it, NavGraphs.main) }
                        },
                        icon = {
                            NavigationIcon(
                                item = item,
                                selected = selectedItemIndex == index
                            )
                        }
                    )
                }
            }
        }
    )
}
