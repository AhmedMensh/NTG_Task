package com.example.ntg_task.ui.characters

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.ntg_task.data.remote.RemoteDataSource
import com.example.ntg_task.domain.entities.DataResult
import com.example.ntg_task.domain.entities.MarvelCharacter
import com.example.ntg_task.domain.usercases.GetAllCharactersUseCase
import javax.inject.Inject

class CharactersPagingSource(
    private val getAllCharactersUseCase: GetAllCharactersUseCase
) : PagingSource<Int, MarvelCharacter>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MarvelCharacter> {
        return try {
            val position = params.key ?: 1
            val response = getAllCharactersUseCase.invoke(position)

            if (response is DataResult.Success) {
                LoadResult.Page(
                    data = response.content?.characters ?: listOf(),
                    prevKey = if (position == 1) null else (position - 1),
                    nextKey = if (position == (response.content?.total
                            ?: 0)
                    ) null else (position + 1)
                )
            } else {
                LoadResult.Error(throw Exception("No Response"))
            }

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MarvelCharacter>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}