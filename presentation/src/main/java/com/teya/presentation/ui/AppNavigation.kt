package com.teya.presentation.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.teya.presentation.viewmodel.AlbumsViewModel

@Composable
fun AppNavigation(navController: NavHostController = rememberNavController()) {
    val viewModel: AlbumsViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = "albums") {
        composable("albums") {
            AlbumsScreen(viewModel, navController)
        }
        composable("detail") {
            AlbumDetailScreen(viewModel)
        }
    }
}