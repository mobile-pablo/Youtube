package com.mobile.pablo.search.view

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import com.mobile.pablo.search.R
import com.mobile.pablo.uicomponents.ext.updateWith
import com.mobile.pablo.uicomponents.theme.secondaryColor
import com.mobile.pablo.uicomponents.theme.secondarySelectedColor
import com.mobile.pablo.uicomponents.theme.spacing
import com.mobile.pablo.uicomponents.theme.tertiaryColor
import com.mobile.pablo.uicomponents.theme.tertiarySelectedColor
import androidx.compose.material.MaterialTheme as Theme

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SearchHistoryChips(
    searchHistory: List<String>,
    query: MutableState<TextFieldValue>,
    modifier: Modifier = Modifier
) {
    var clickedChip by remember { mutableIntStateOf(DEFAULT_CHIP_INDEX) }
    FlowRow(
        modifier = modifier.padding(
            horizontal = Theme.spacing.spacing_10,
            vertical = Theme.spacing.spacing_8
        )
    ) {
        searchHistory.forEachIndexed { index, item ->
            InputChip(
                modifier = Modifier.padding(horizontal = Theme.spacing.spacing_4),
                selected = clickedChip == index,
                onClick = {
                    clickedChip = index
                    query.updateWith(item)
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_history_24),
                        contentDescription = null
                    )
                },
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = Theme.colors.secondaryColor,
                    labelColor = Theme.colors.tertiaryColor,
                    iconColor = Theme.colors.tertiaryColor,
                    selectedContainerColor = Theme.colors.secondarySelectedColor,
                    selectedLabelColor = Theme.colors.tertiarySelectedColor,
                    selectedLeadingIconColor = Theme.colors.tertiarySelectedColor
                ),
                label = { Text(text = item) }
            )
        }
    }
}

private const val DEFAULT_CHIP_INDEX = -1
