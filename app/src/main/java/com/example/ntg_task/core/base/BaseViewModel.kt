package com.example.ntg_task.core.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ntg_task.domain.entities.DataResult
import kotlinx.coroutines.launch


abstract class BaseViewModel : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun clearError() {
        _error.value = null
    }


    fun <T> executeApiWithState(
        data: MutableLiveData<T>,
        function: suspend () -> DataResult<T>
    ) {
        viewModelScope.launch {
            _loading.value = true
            when (val result = function.invoke()) {
                is DataResult.Success -> {
                    _loading.value = false

                    result.content?.let {
                        data.value = result.content
                    }
                }

                is DataResult.Error -> {
                    _loading.value = false
                    _error.value = result.exception.message
                }
            }
        }


    }
}