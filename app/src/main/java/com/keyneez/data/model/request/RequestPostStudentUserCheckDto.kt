package com.keyneez.data.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestPostStudentUserCheckDto(
    @SerialName("user_school")
    val school: String,
    @SerialName("user_name")
    val name: String,
    @SerialName("user_ocr")
    val ocrImg: String,
    @SerialName("ocr_dir")
    val ocrDir: Boolean
)
