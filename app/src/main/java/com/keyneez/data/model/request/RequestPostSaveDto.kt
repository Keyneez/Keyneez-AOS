package com.keyneez.data.model.request

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class RequestPostSaveDto(
    @SerialName("user_key")
    val key: Int,
    @SerialName("content_id")
    val content: Int

)
