package com.keyneez.data.repository

import com.keyneez.data.model.response.ResponseLikeDto
import com.keyneez.data.model.response.wrapper.BaseResponse

interface ContentRepository {
    suspend fun getLike(): Result<BaseResponse<List<ResponseLikeDto>>>
}
