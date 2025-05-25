package com.teya.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.teya.presentation.viewmodel.AlbumsViewModel
import androidx.compose.runtime.getValue

@Composable
fun AlbumDetailScreen(viewModel: AlbumsViewModel) {
    val album by viewModel.selectedAlbum.collectAsState()

    album?.let {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = it.title, style = MaterialTheme.typography.titleLarge)
            Text(text = "By ${it.artist}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Genre: ${it.category}")
            Text(text = "Released: ${it.releaseDate}")
            AsyncImage(model = it.imageUrl, contentDescription = null)
        }
    } ?: Text("No album data found")
}
