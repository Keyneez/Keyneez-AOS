package com.keyneez.data.repository

import com.keyneez.data.model.response.ResponseGetContentDeatilDto
import com.keyneez.data.model.response.wrapper.BaseResponse

interface ContentRepository {
    suspend fun getDetail(contentId: Int): Result<BaseResponse<ResponseGetContentDeatilDto>>
}
