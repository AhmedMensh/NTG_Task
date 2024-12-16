package com.example.ntg_task.ui.character_profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.ntg_task.core.base.BaseViewModel
import com.example.ntg_task.domain.entities.Comic
import com.example.ntg_task.domain.usercases.GetCharacterComicsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterProfileViewModel @Inject constructor(
    private val getCharacterComicsUseCase: GetCharacterComicsUseCase
) : BaseViewModel() {

    var allComics: MutableList<Comic> = mutableListOf()
    private val _comics = MutableLiveData<List<Comic>>()
    val comics: LiveData<List<Comic>> = _comics

    fun getCharacterComics(comicId: Int) {
        viewModelScope.launch {
            executeApiWithState(_comics) {
                getCharacterComicsUseCase.invoke(comicId)
            }

        }
    }

}