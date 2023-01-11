package com.keyneez.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class RequestPatchUserTypeDto(
    val disposition: String,
    val interest: List<String>
)
