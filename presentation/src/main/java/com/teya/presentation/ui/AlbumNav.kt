package com.teya.presentation.ui

sealed class Screen(val route: String) {
    object Albums : Screen("albums")
    object AlbumDetail : Screen("album_detail")
}
