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

class GetCharacterComicsUseCase @Inject constructor(
    private val iCharactersRepository: ICharactersRepository
) {

    suspend operator fun invoke(comicId: Int): DataResult<List<Comic>> {

        return when (val response = iCharactersRepository.getCharacterComics(comicId)) {
            is NetworkResult.Success -> {
                DataResult.Success(
                    response.content?.data?.results?.map {
                        Comic(
                            name = it.name.orEmpty(),
                            resourceURI = it.resourceURI.orEmpty(),
                            thumbnail = it.thumbnail.let {
                                Thumbnail(
                                    path = it?.path.orEmpty(),
                                    extension = it?.extension.orEmpty()

                                )
                            }
                        )
                    }

                )
            }

            is NetworkResult.Error -> DataResult.Error(response.exception)
        }
    }
}