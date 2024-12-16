package com.example.ntg_task.domain.usercases

import com.example.ntg_task.data.models.NetworkResult
import com.example.ntg_task.domain.entities.Comic
import com.example.ntg_task.domain.entities.DataResult
import com.example.ntg_task.domain.entities.MarvelCharacter
import com.example.ntg_task.domain.entities.MarvelCharacterUrl
import com.example.ntg_task.domain.entities.MarvelCharacters
import com.example.ntg_task.domain.entities.Thumbnail
import com.example.ntg_task.domain.repositories.ICharactersRepository
import javax.inject.Inject

class GetAllCharactersUseCase @Inject constructor(
    private val iCharactersRepository: ICharactersRepository
) {

    suspend operator fun invoke(offset: Int): DataResult<MarvelCharacters> {
        return when (val response = iCharactersRepository.getAllCharacters(offset)) {
            is NetworkResult.Success -> {
                DataResult.Success(
                    MarvelCharacters(
                        characters = response.content?.data?.results?.map {
                            MarvelCharacter(
                                name = it.name.orEmpty(),
                                id = it.id ?: -1,
                                urls = it.urls?.map {
                                    MarvelCharacterUrl(
                                        url = it.url.orEmpty()
                                    )
                                } ?: listOf(),
                                description = it.description.orEmpty(),
                                thumbnail = it.thumbnail.let {
                                    Thumbnail(
                                        path = it?.path.orEmpty(),
                                        extension = it?.extension.orEmpty()
                                    )
                                },
                                comics = it.comics?.items?.map {
                                    Comic(
                                        name = it?.name.orEmpty(),
                                        resourceURI = it?.resourceURI.orEmpty()
                                    )
                                } ?: listOf()
                            )
                        },
                        total = response.content?.total ?: 0
                    )
                )
            }

            is NetworkResult.Error -> DataResult.Error(response.exception)
        }
    }
}