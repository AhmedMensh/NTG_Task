package com.example.ntg_task.data.remote


import android.util.Log
import com.example.ntg_task.data.models.NetworkResult
import retrofit2.Response
import javax.inject.Inject


class SafeApiCall @Inject constructor() {

    suspend operator fun <T> invoke(apiCall: suspend () -> Response<T>): NetworkResult<T> {

        return try {
            val result = apiCall()
            if (result.isSuccessful) {
                Log.d("TAG", "invoke: $result")
                return NetworkResult.Success(result.body())
            } else {
                return NetworkResult.Error(
                    Exception(result.message())
                )
            }
        } catch (e: Exception) {
            Log.d("TAG", "invoke: ${e.message}")
            NetworkResult.Error(e)
//            when (e) {
//                is HttpException -> {
//                    val errorBodyString = e.response()?.errorBody()?.string()
//                    var errorBodyJson: NetworkFailure? = null
//                    if (errorBodyString != null) {
//                        try {
//                            errorBodyJson = jsonAdapter.fromJson(errorBodyString)
//                        } catch (e: Exception) {
//                            e.printStackTrace()
//                        }
//                    }
//                    when (e.code()) {
//                        in 300 until 400 -> {
//                            NetworkResult.Error(Exception("Unauthorized"))
//                        }
//                        in 400 until 500 -> {
//                            NetworkResult.Error(
//                                Exception(
//                                    errorBodyJson?.message
//                                )
//                            )
//                        }
//                        in 500 until 600 -> {
//                            NetworkResult.Error(Exception("Server error please try again."))
//                        }
//                        else -> {
//                            NetworkResult.Error(Exception("something went wrong"))
//                        }
//                    }
//                }
//                is IOException -> {
//                    NetworkResult.Error(Exception("No internet connection"))
//                }
//                else -> {
//                    NetworkResult.Error(Exception("something went wrong"))
//                }
//            }
        }
    }
}