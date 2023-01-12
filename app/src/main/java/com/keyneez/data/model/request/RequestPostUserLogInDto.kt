package com.keyneez.data.model.request

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class RequestPostUserLogInDto(
    @SerialName("user_phone")
    val phone: String,
    @SerialName("user_password")
    val password: String
)
