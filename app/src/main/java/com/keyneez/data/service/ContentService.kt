package com.keyneez.data.service

import com.keyneez.data.model.request.RequestLikeDto
import com.keyneez.data.model.response.ResponseLikeDto
import com.keyneez.data.model.response.wrapper.BaseResponse
import retrofit2.http.Body
import retrofit2.http.GET

interface ContentService {
    @GET("api/content")
    suspend fun getLike(
        @Body response: RequestLikeDto
    ): BaseResponse<List<ResponseLikeDto>>
}
