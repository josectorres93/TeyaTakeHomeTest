package com.teya.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teya.data.repository.AlbumRepository
import com.teya.data.dto.toDomainModel
import com.teya.domain.model.Album
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumsViewModel @Inject constructor(
    private val repository: AlbumRepository
) : ViewModel() {

    private val _albums = MutableStateFlow<List<Album>>(emptyList())
    val albums: StateFlow<List<Album>> = _albums

    // NEW: For selected album
    private val _selectedAlbum = MutableStateFlow<Album?>(null)
    val selectedAlbum: StateFlow<Album?> = _selectedAlbum

    fun selectAlbum(album: Album) {
        _selectedAlbum.value = album
    }

    init {
        viewModelScope.launch {
            try {
                repository.getAlbuns()?.feed?.entries?.map { it.toDomainModel() }?.let {
                    _albums.value = it
                }
            } catch (e: Exception) {
                Log.e("AlbumsViewModel", "Error fetching albums", e)
            }
        }
    }
}
