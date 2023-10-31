package com.mobile.pablo.home.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun HomeScreen(
    destinationsNavigator: DestinationsNavigator,
    navController: NavController = rememberNavController(),
    viewModel: HomeViewModel = hiltViewModel()
) {
    val homeState = viewModel.homeState.value
    when (homeState) {
        is HomeState.Loading -> {
            Text(text = "Loading")
        }

        is HomeState.Done -> {
            LazyColumn {
                items(homeState.data.items!!) { item ->
                    Text(text = item!!.snippet!!.description!!)
                }
            }
        }

        else -> {
            Text(text = "Error")
        }
    }
}