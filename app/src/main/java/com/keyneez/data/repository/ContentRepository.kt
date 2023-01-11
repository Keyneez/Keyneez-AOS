package com.keyneez.data.repository

import com.keyneez.data.model.request.RequestPostSaveDto
import com.keyneez.data.model.response.ResponseContentDto
import com.keyneez.data.model.response.wrapper.BaseResponse

interface ContentRepository {
    suspend fun getContent(): Result<BaseResponse<ResponseContentDto>>
    suspend fun postSave(
        requestPostSaveDto: RequestPostSaveDto
    ): Result<BaseResponse<Void>>
}
