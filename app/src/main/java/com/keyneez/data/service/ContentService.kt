package com.keyneez.data.service

import com.keyneez.data.model.response.ResponseGetContentDeatilDto
import com.keyneez.data.model.response.ResponseLikeDto
import com.keyneez.data.model.response.wrapper.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ContentService {
    @GET("content/liked")
    suspend fun getLike(): BaseResponse<List<ResponseLikeDto>>

    @GET("content/view/{content_id}")
    suspend fun getDetail(
        @Path("content_id") contentId: Int
    ): BaseResponse<ResponseGetContentDeatilDto>
}
