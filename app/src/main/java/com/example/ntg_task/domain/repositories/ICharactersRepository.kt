package com.example.ntg_task.domain.repositories

import com.example.ntg_task.data.models.CharactersResponse
import com.example.ntg_task.data.models.ComicsResponse
import com.example.ntg_task.data.models.NetworkResult


interface ICharactersRepository {

    suspend fun getAllCharacters(offset: Int) : NetworkResult<CharactersResponse>
    suspend fun getCharacterComics(comicId: Int) : NetworkResult<ComicsResponse>
}