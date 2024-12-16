package com.example.ntg_task.domain.entities

sealed class DataResult<out T> {
    data class Success<T>(val content: T?) : DataResult<T>()
    data class Error<T>(val exception: Exception) : DataResult<T>()

}