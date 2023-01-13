package com.keyneez.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponsePostUserCheckDto(
    @SerialName("user_key")
    val key: Int,
    @SerialName("user_name")
    val name: String,
    @SerialName("user_age")
    val age: String?,
    @SerialName("user_gender")
    val gender: String?,
    @SerialName("user_birth")
    val birthDate: String?,
    @SerialName("user_school")
    val school: String?,
    @SerialName("user_character")
    val character: Int?,
    @SerialName("user_password")
    val password: String?,
    @SerialName("user_ocr")
    val ocrImg: String?,
    @SerialName("ocr_dir")
    val ocrDir: Boolean?,
    @SerialName("user_benefit")
    val benefit: Boolean?,
    val Characters: Character?
) {
    @Serializable
    data class Character(
        @SerialName("character_key")
        val key: Int,
        val inter: String?,
        val dispo: String?,
        val character: String?,
        @SerialName("character_img")
        val img: String?,
        @SerialName("character_desc")
        val description: String?,
        @SerialName("test_img")
        val testImg: String?
    )
}