package com.keyneez.data.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestPostPwdCheckDto(
    @SerialName("user_password")
    val password: String
)
