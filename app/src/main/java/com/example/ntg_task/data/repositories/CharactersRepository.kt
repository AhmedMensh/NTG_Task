package com.example.ntg_task.data.repositories

import com.example.ntg_task.data.models.CharactersResponse
import com.example.ntg_task.data.models.ComicsResponse
import com.example.ntg_task.data.models.NetworkResult
import com.example.ntg_task.data.remote.RemoteDataSource
import com.example.ntg_task.domain.repositories.ICharactersRepository
import javax.inject.Inject


class CharactersRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
): ICharactersRepository {
    override suspend fun getAllCharacters(offset: Int): NetworkResult<CharactersResponse> {
        return remoteDataSource.getAllCharacters(offset)
    }

    override suspend fun getCharacterComics(comicId: Int): NetworkResult<ComicsResponse> {
        return remoteDataSource.getCharacterComics(comicId)
    }

}