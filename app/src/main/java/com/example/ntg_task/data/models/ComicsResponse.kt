package com.example.ntg_task.data.models

import com.squareup.moshi.Json

class ComicsResponse(
    @Json(name = "data")
    val data: ComicsResults? = null,
    @Json(name = "total")
    val total: Int? = null
)

data class ComicsResults(
    @Json(name = "results")
    val results: List<ComicsModel.Item>
)
