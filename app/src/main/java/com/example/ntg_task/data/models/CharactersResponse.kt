package com.example.ntg_task.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class CharactersResponse(
    @Json(name = "data")
    val data: CharacterResults? = null,
    @Json(name = "total")
    val total: Int? = null
)

data class CharacterResults(
    @Json(name = "results")
    val results: List<MarvelCharacterModel>
)

data class MarvelCharacterModel(
    @Json(name = "id")
    val id: Int? = null,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "description")
    val description: String? = null,
    @Json(name = "thumbnail")
    val thumbnail: ThumbnailModel? = null,
    @Json(name = "urls")
    val urls: List<MarvelCharacterUrlModel>? = null,
    @Json(name = "comics")
    val comics: ComicsModel? = null
)

data class ThumbnailModel(
    @Json(name = "path")
    val path: String? = null,
    @Json(name = "extension")
    val extension: String? = null
)

data class MarvelCharacterUrlModel(
    @Json(name = "url")
    val url: String? = null
)

data class ComicsModel(
    @Json(name = "available")
    val available: Int?,
    @Json(name = "collectionURI")
    val collectionURI: String?,
    @Json(name = "items")
    val items: List<Item?>?,
    @Json(name = "returned")
    val returned: Int?
) {
    data class Item(
        @Json(name = "name")
        val name: String?,
        @Json(name = "resourceURI")
        val resourceURI: String?,
        @Json(name = "thumbnail")
        val thumbnail: ThumbnailModel? = null,
    )
}