package com.teya.presentation.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.teya.data.dto.*
import com.teya.data.repository.AlbumRepository
import com.teya.domain.model.Album
import com.teya.presentation.viewmodel.AlbumsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.*
import org.mockito.Mockito.*

@ExperimentalCoroutinesApi
class AlbumsViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = UnconfinedTestDispatcher()
    private lateinit var repository: AlbumRepository
    private lateinit var viewModel: AlbumsViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repository = mock(AlbumRepository::class.java)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `init should fetch albums and update albums StateFlow`() = runTest {
        // Arrange: Create mock AlbumDto entries
        val mockAlbumDto1 = AlbumDto(
            name = LabelWrapper("Name1"),
            images = listOf(
                ImageWrapper("url1", ImageAttributes("100"))
            ),
            artist = ArtistWrapper("Artist1", null),
            category = CategoryWrapper(CategoryAttributes("Genre1", "term1", "scheme1", "id1")),
            releaseDate = ReleaseDateWrapper("2021-01-01", LabelWrapper("2021-01-01")),
            title = LabelWrapper("Title1")
        )

        val mockAlbumDto2 = AlbumDto(
            name = LabelWrapper("Name2"),
            images = listOf(
                ImageWrapper("url2", ImageAttributes("100"))
            ),
            artist = ArtistWrapper("Artist2", null),
            category = CategoryWrapper(CategoryAttributes("Genre2", "term2", "scheme2", "id2")),
            releaseDate = ReleaseDateWrapper("2021-02-01", LabelWrapper("2021-02-01")),
            title = LabelWrapper("Title2")
        )

        val mockFeedDto = FeedDto(entries = listOf(mockAlbumDto1, mockAlbumDto2))
        val mockFeedResponseDto = FeedResponseDto(feed = mockFeedDto)

        // Mock the repository to return the mock FeedResponseDto
        `when`(repository.getAlbuns()).thenReturn(mockFeedResponseDto)

        // Act: Initialize the ViewModel
        viewModel = AlbumsViewModel(repository)

        // Assert: Verify that the albums StateFlow emits the expected list
        viewModel.albums.test {
            val albums = awaitItem()
            val expectedAlbums = listOf(mockAlbumDto1.toDomainModel(), mockAlbumDto2.toDomainModel())
            Assert.assertEquals(expectedAlbums, albums)
            cancelAndIgnoreRemainingEvents()
        }
    }


    @Test
    fun `selectAlbum should update selectedAlbum StateFlow`() = runTest {
        // Arrange
        val album = Album(
            name = "Name1",
            artist = "Artist1",
            imageUrl = "url1",
            category = "Genre1",
            releaseDate = "2021-01-01",
            title = "Title1"
        )
        viewModel = AlbumsViewModel(repository)

        // Act
        viewModel.selectAlbum(album)

        // Assert
        viewModel.selectedAlbum.test {
            val selected = awaitItem()
            Assert.assertEquals(album, selected)
            cancelAndIgnoreRemainingEvents()
        }
    }

}
