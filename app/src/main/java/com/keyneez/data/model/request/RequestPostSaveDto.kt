package com.keyneez.data.model.request

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class RequestPostSaveDto(
    @SerialName("content_id")
    val contentId: Int
)
