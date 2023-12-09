package com.mobile.pablo.uicomponents.views

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import com.mobile.pablo.uicomponents.theme.spacing
import com.mobile.pablo.uicomponents.theme.tertiaryColor
import androidx.compose.material.MaterialTheme as Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarView(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit
) {
    SearchBar(
        query = searchQuery,
        onQueryChange = onSearchQueryChange,
        onSearch = onSearch,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                tint = Theme.colors.tertiaryColor,
                contentDescription = null
            )
        },
        trailingIcon = {},
        content = {},
        active = true,
        onActiveChange = {},
        tonalElevation = Theme.spacing.default
    )
}
