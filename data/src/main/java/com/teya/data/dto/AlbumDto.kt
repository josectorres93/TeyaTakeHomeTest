package com.teya.data.dto

import com.google.gson.annotations.SerializedName
import com.teya.domain.model.Album

data class FeedResponseDto(
    @SerializedName("feed") val feed: FeedDto
)

data class FeedDto(
    @SerializedName("entry") val entries: List<AlbumDto>
)

data class AlbumDto(
    @SerializedName("im:name") val name: LabelWrapper,
    @SerializedName("im:image") val images: List<ImageWrapper>,
    @SerializedName("im:artist") val artist: ArtistWrapper,
    @SerializedName("category") val category: CategoryWrapper,
    @SerializedName("im:releaseDate") val releaseDate: ReleaseDateWrapper,
    @SerializedName("title") val title: LabelWrapper
)

data class LabelWrapper(
    @SerializedName("label") val label: String
)

data class ImageWrapper(
    @SerializedName("label") val url: String,
    @SerializedName("attributes") val attributes: ImageAttributes
)

data class ImageAttributes(
    @SerializedName("height") val height: String
)

data class CategoryWrapper(
    @SerializedName("attributes") val attributes: CategoryAttributes
)

data class CategoryAttributes(
    @SerializedName("label") val label: String,
    @SerializedName("term") val term: String,
    @SerializedName("scheme") val scheme: String,
    @SerializedName("im:id") val id: String
)

data class ReleaseDateWrapper(
    @SerializedName("label") val isoDate: String,
    @SerializedName("attributes") val attributes: LabelWrapper
)

data class ArtistWrapper(
    @SerializedName("label") val label: String,
    @SerializedName("attributes") val attributes: HrefWrapper?
)

data class HrefWrapper(
    @SerializedName("href") val href: String
)

fun AlbumDto.toDomainModel(): Album {
    return Album(
        name = name.label,
        artist = artist.label,
        imageUrl = images.last().url,
        category = category.attributes.label,
        releaseDate = releaseDate.attributes.label,
        title = title.label
    )
}
