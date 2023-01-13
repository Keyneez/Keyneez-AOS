package com.keyneez.data.repository

import com.keyneez.data.model.response.ResponseGetContentDeatilDto
import com.keyneez.data.model.response.ResponseGetSearchDto
import com.keyneez.data.model.response.ResponseLikeDto
import com.keyneez.data.model.response.wrapper.BaseResponse

interface ContentRepository {
    suspend fun getLike(): Result<BaseResponse<List<ResponseLikeDto>>>

    suspend fun getDetail(contentId: Int): Result<BaseResponse<ResponseGetContentDeatilDto>>

    suspend fun getSearch(keyword: String): Result<BaseResponse<List<ResponseGetSearchDto>>>
}
