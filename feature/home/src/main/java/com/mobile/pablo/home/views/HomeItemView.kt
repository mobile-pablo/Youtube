package com.mobile.pablo.home.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import com.mobile.pablo.home.wrapper.HomeItemWrapper

@Composable
fun HomeItemView(wrapper: HomeItemWrapper) {
    Row {
        Column {
            AsyncImage(
                model = wrapper.imageUrl,
                contentDescription = null
            )
        }
        Row {
            Text(text = wrapper.title)
            Text(text = wrapper.description)
        }
    }
}