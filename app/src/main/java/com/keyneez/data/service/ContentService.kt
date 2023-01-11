package com.keyneez.data.service

import com.keyneez.data.model.response.ResponseLikeDto
import com.keyneez.data.model.response.wrapper.BaseResponse
import retrofit2.http.GET

interface ContentService {
    @GET("content/liked")
    suspend fun getLike(): BaseResponse<List<ResponseLikeDto>>
}
