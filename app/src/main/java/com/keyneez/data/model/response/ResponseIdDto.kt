package com.keyneez.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseIdDto(
    @SerialName("user_key") val key: Int,
    @SerialName("user_name") val name: String,
    @SerialName("user_age") val age: String?,
    @SerialName("user_gender") val gender: String?,
    @SerialName("user_phone") val phone: String,
    @SerialName("user_birth") val birth: String,
    @SerialName("user_school") val school: String?,
    @SerialName("user_character") val userCharacter: Int?,
    @SerialName("user_password") val password: String?,
    @SerialName("user_benefit") val benefit: Boolean?,
    @SerialName("user_ocr") val ocrImg: String?,
    @SerialName("ocr_dir") val ocrDir: Boolean?,
    val Characters: Character?
) {
    @Serializable
    data class Character(
        @SerialName("character_key") val characterKey: Int?,
        val dispo: String,
        val inter: String,
        val character: String?,
        @SerialName("character_img") val characterImg: String?,
        @SerialName("character_desc") val characterDesc: String?,
        @SerialName("test_img") val testImg: String?
    )
}
