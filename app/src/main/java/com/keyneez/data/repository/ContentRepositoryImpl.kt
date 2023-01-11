package com.keyneez.data.repository

import com.keyneez.data.model.request.RequestPostSaveDto
import com.keyneez.data.model.response.ResponseContentDto
import com.keyneez.data.model.response.wrapper.BaseResponse
import com.keyneez.data.source.ContentDataSource
import javax.inject.Inject

class ContentRepositoryImpl @Inject constructor(
    private val contentDataSource: ContentDataSource
) : ContentRepository {

    override suspend fun getContent(): Result<BaseResponse<ResponseContentDto>> =
        kotlin.runCatching { contentDataSource.getContent() }

    override suspend fun postSave(
        requestPostSaveDto: RequestPostSaveDto
    ): Result<BaseResponse<Void>> =
        kotlin.runCatching { contentDataSource.postSave(requestPostSaveDto) }
}
