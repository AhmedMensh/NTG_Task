package com.example.ntg_task.data.remote

import com.example.ntg_task.data.models.CharactersResponse
import com.example.ntg_task.data.models.ComicsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelServices {
    @GET("/v1/public/characters")
    suspend fun getAllCharacters(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int
    ): Response<CharactersResponse>

    @GET("/v1/public/comics/{comicId}")
    suspend fun getCharacterComics(
        @Path("comicId") comicId: Int
    ): Response<ComicsResponse>
}