package com.keyneez.data.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestLikeDto(
    @SerialName("user_key")
    val key: Int
)
