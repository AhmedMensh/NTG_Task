package com.example.ntg_task.data.models

sealed class NetworkResult<out T> {
    data class Success<T>(val content: T?) : NetworkResult<T>()
    data class Error<T>(val exception: Exception) : NetworkResult<T>()
}