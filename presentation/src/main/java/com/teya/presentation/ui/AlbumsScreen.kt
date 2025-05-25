package com.teya.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.teya.presentation.viewmodel.AlbumsViewModel

@Composable
fun AlbumsScreen(viewModel: AlbumsViewModel, navController: NavController) {
    val albums by viewModel.albums.collectAsState()

    LazyColumn {
        items(albums) { album ->
            ListItem(
                headlineContent = { Text(album.name) },
                supportingContent = { Text(album.artist) },
                leadingContent = {
                    AsyncImage(model = album.imageUrl, contentDescription = null)
                },
                modifier = Modifier.clickable {
                    viewModel.selectAlbum(album)
                    navController.navigate("detail")
                }
            )
        }
    }
}