package com.keyneez.data.model.response

@kotlinx.serialization.Serializable
data class ResponsePostSaveDto(
    val status: Int,
    val message: String
)
