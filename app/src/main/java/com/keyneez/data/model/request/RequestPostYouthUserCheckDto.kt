package com.keyneez.data.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestPostYouthUserCheckDto(
    @SerialName("user_name")
    val name: String,
    @SerialName("user_birth")
    val birth: String,
    @SerialName("user_ocr")
    val ocrImg: String,
    @SerialName("ocr_dir")
    val ocrDir: Boolean
)
