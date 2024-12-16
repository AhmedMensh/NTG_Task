package com.example.ntg_task.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


data class MarvelCharacters(
    val characters: List<MarvelCharacter>? = null,
    val total: Int? = null
)

@Parcelize
data class MarvelCharacter(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail,
    val urls: List<MarvelCharacterUrl>,
    val comics: List<Comic>,
) : Parcelable

@Parcelize
data class Thumbnail(
    val path: String,
    val extension: String
) : Parcelable

@Parcelize
data class MarvelCharacterUrl(
    val url: String
) : Parcelable

@Parcelize
data class Comic(
    val name: String,
    val resourceURI: String,
    var thumbnail: Thumbnail? = null
) : Parcelable