package com.keyneez.data.service

import com.keyneez.data.model.response.wrapper.BaseResponse
import com.keyneez.data.model.response.wrapper.ResponseContentDto
import retrofit2.http.GET

interface ContentService {

    // 게시물 전체 조회 API
    @GET("content/")
    suspend fun getContent(): BaseResponse<ResponseContentDto>
}
