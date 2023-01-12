package com.keyneez.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponsePatchPwdSignupDto(
    @SerialName("user_name")
    val name: String,
    @SerialName("user_phone")
    val phone: String,
    @SerialName("user_password")
    val password: String
)
