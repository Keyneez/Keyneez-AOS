package com.keyneez.data.model.response.wrapper

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class ResponseContentDto(
    @SerialName("content_key")
    val key: Int,
    @SerialName("content_title")
    val title: String,
    @SerialName("content_img")
    val img: String,
    val introduction: String,
    val usage: String,
    @SerialName("start_at")
    val start: String,
    @SerialName("end_at")
    val end: String,
    val liked: Boolean,
    val category: List<String>
)
