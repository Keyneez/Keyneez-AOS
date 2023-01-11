package com.keyneez.data.service

import com.keyneez.data.model.request.RequestPostSaveDto
import com.keyneez.data.model.response.ResponseContentDto
import com.keyneez.data.model.response.ResponsePostSaveDto
import com.keyneez.data.model.response.wrapper.BaseResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ContentService {

    // 게시물 전체 조회 API
    @GET("content/")
    suspend fun getContent(): BaseResponse<ResponseContentDto>

    // 게시물 찜
    @POST("content/save")
    suspend fun postLike(
        @Body requestBody: RequestPostSaveDto
    ): ResponsePostSaveDto
}
