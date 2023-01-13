package com.keyneez.data.service

import com.keyneez.data.model.request.RequestPostSaveDto
import com.keyneez.data.model.response.ResponseContentDto
import com.keyneez.data.model.response.ResponseGetContentDeatilDto
import com.keyneez.data.model.response.ResponseGetSearchDto
import com.keyneez.data.model.response.ResponseLikeDto
import com.keyneez.data.model.response.wrapper.BaseResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ContentService {
    @GET("content/liked")
    suspend fun getLike(): BaseResponse<List<ResponseLikeDto>>

    @GET("content/view/{content_id}")
    suspend fun getDetail(
        @Path("content_id") contentId: Int
    ): BaseResponse<ResponseGetContentDeatilDto>

    @GET("content/search")
    suspend fun getSearch(
        @Query("keyword") keyword: String
    ): BaseResponse<List<ResponseGetSearchDto>>

    // 게시물 전체 조회 API
    @GET("content/")
    suspend fun getContent(): BaseResponse<List<ResponseContentDto>>

    // 게시물 찜
    @POST("content/save")
    suspend fun postSave(
        @Body requestBody: RequestPostSaveDto
    ): BaseResponse<Unit>
}
