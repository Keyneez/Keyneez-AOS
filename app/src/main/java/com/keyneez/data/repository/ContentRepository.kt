package com.keyneez.data.repository

import com.keyneez.data.model.request.RequestLikeDto
import com.keyneez.data.model.response.ResponseLikeDto
import com.keyneez.data.model.response.wrapper.BaseResponse

interface ContentRepository {
    suspend fun getLike(requestLikeDto: RequestLikeDto): Result<BaseResponse<List<ResponseLikeDto>>>
}
