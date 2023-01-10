package com.keyneez.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseLikeDto(
    @SerialName("content_key")
    val key: Int,
    @SerialName("content_title")
    val title: String,
    @SerialName("start_at")
    val start: String,
    @SerialName("start_end")
    val end: String,
    @SerialName("content_img")
    val background: String
)
