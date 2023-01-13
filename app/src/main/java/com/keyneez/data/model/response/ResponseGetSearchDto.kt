package com.keyneez.data.model.response

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class ResponseGetSearchDto(
    @SerialName("content_key")
    val key: Int,
    @SerialName("content_title")
    val title: String,
    @SerialName("start_at")
    val start: String?,
    @SerialName("end_at")
    val end: String?,
    @SerialName("content_img")
    val background: String?,
    val liked: Boolean
)
