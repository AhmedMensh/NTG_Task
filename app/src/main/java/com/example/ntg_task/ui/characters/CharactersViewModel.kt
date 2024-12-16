package com.example.ntg_task.ui.characters

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.ntg_task.core.base.BaseViewModel
import com.example.ntg_task.domain.entities.MarvelCharacter
import com.example.ntg_task.domain.usercases.GetAllCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getAllCharactersUseCase: GetAllCharactersUseCase
) : BaseViewModel() {

    fun getAllCharacters(): LiveData<PagingData<MarvelCharacter>> = Pager(
        config = PagingConfig(pageSize = 10, maxSize = 200),
        pagingSourceFactory = { CharactersPagingSource(getAllCharactersUseCase) }
    ).liveData



}