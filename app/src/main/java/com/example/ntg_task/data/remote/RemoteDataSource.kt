package com.example.ntg_task.data.remote

import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: MarvelServices,
    private val safeApiCall: SafeApiCall
) {
    suspend fun getAllCharacters(
        offset: Int
    ) = safeApiCall { apiService.getAllCharacters(offset = offset) }

    suspend fun getCharacterComics(
        comicId: Int
    ) = safeApiCall { apiService.getCharacterComics(comicId = comicId) }


}