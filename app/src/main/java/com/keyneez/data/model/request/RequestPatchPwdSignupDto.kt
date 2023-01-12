package com.keyneez.data.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestPatchPwdSignupDto(
    @SerialName("user_password")
    val password: String
)
