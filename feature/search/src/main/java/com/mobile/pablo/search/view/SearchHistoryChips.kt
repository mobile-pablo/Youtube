package com.mobile.pablo.search.view

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.mobile.pablo.search.R
import com.mobile.pablo.uicomponents.theme.secondaryColor
import com.mobile.pablo.uicomponents.theme.spacing
import com.mobile.pablo.uicomponents.theme.tertiaryColor
import androidx.compose.material.MaterialTheme as Theme

typealias OnClick = (query: String) -> Unit

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SearchHistoryChips(
    searchHistory: List<String>,
    onClick: OnClick,
    modifier: Modifier = Modifier
) {
    var clickedChip by remember { mutableIntStateOf(DEFAULT_CHIP_INDEX) }

    FlowRow(
        modifier = modifier.padding(
            horizontal = Theme.spacing.spacing_10,
            vertical = Theme.spacing.spacing_8
        )
    ) {
        searchHistory.forEach { item ->
            AssistChip(
                modifier = Modifier.padding(horizontal = Theme.spacing.spacing_4),
                onClick = {
                    onClick(item)
                    clickedChip = DEFAULT_CHIP_INDEX
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_history_24),
                        contentDescription = null
                    )
                },
                colors = AssistChipDefaults.assistChipColors(
                    containerColor = Theme.colors.secondaryColor,
                    labelColor = Theme.colors.tertiaryColor,
                    leadingIconContentColor = Theme.colors.tertiaryColor
                ),
                label = { Text(text = item) }
            )
        }
    }
}

private const val DEFAULT_CHIP_INDEX = -1
