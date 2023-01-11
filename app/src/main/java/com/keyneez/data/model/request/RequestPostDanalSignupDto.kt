package com.keyneez.data.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestPostDanalSignupDto(
    @SerialName("user_name")
    val name: String,
    @SerialName("user_birth")
    val birth: String,
    @SerialName("user_gender")
    val gender: String,
    @SerialName("user_phone")
    val phone: String
)
