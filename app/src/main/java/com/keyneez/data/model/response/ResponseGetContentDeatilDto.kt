package com.keyneez.data.model.response

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class ResponseGetContentDeatilDto(
    @SerialName("content_key")
    val key: Int,
    @SerialName("content_title")
    val title: String,
    @SerialName("content_link")
    val link: String,
    @SerialName("content_img")
    val img: String,
    @SerialName("place")
    val place: String,
    @SerialName("introdution")
    val introdution: String,
    @SerialName("benefit")
    val benefit: String,
    @SerialName("usage")
    val usage: String,
    @SerialName("start_at")
    val start: String,
    @SerialName("end_at")
    val end: String,
    @SerialName("liked")
    val liked: Boolean,
    @SerialName("category")
    val category: Category
) {
    @kotlinx.serialization.Serializable
    data class Category(
        val category: String?
    )
}
