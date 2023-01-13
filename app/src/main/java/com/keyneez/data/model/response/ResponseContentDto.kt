package com.keyneez.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseContentDto(
    @SerialName("content_key")
    val key: Int,
    @SerialName("content_title")
    val title: String,
    @SerialName("content_img")
    val img: String?,
    val introduction: String,
    val usage: String,
    @SerialName("start_at")
    val start: String?,
    @SerialName("end_at")
    val end: String?,
    val liked: Boolean,
    val category: List<String>
) {
    @Serializable
    data class Category(
        val category: String?
    )
}
