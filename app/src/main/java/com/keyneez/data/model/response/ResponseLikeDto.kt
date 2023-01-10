package com.keyneez.data.model.response

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class ResponseLikeDto(
    val status: Int,
    val message: String,
    val data: Data
) {
    data class Data(
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
}
