package com.mobile.pablo.youtube.nav.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.NavigationRail
import androidx.compose.material.NavigationRailItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.util.fastForEachIndexed
import coil.compose.SubcomposeAsyncImage
import com.mobile.pablo.youtube.R
import com.mobile.pablo.youtube.nav.model.NavigationItem

@Composable
fun NavigationSideBar(
    modifier: Modifier = Modifier,
    items: List<NavigationItem>,
    selectedItemIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    NavigationRail(
        modifier = modifier,
        header = {
            SubcomposeAsyncImage(
                model = Image(
                    painter = painterResource(id = R.drawable.ic_youtube_34),
                    contentDescription = null
                ),
                contentDescription = null
            )
        },
        content = {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxHeight()
            ) {
                items.fastForEachIndexed { index, item ->
                    NavigationRailItem(
                        selected = selectedItemIndex == index,
                        onClick = { onItemSelected(index) },
                        icon = {
                            Image(
                                imageVector = if (selectedItemIndex == index)
                                    item.selectedIcon
                                else
                                    item.unselectedIcon,
                                contentDescription = null
                            )
                        },
                        label = {
                            Text(text = item.title)
                        }
                    )
                }
            }
        }
    )
}