package com.keyneez.data.repository

import com.keyneez.data.model.request.RequestPostSaveDto
import com.keyneez.data.model.response.ResponseContentDto
import com.keyneez.data.model.response.ResponseGetContentDeatilDto
import com.keyneez.data.model.response.ResponseLikeDto
import com.keyneez.data.model.response.wrapper.BaseResponse
import com.keyneez.data.source.ContentDataSource
import javax.inject.Inject

class ContentRepositoryImpl @Inject constructor(
    private val contentDataSource: ContentDataSource
) : ContentRepository {

    override suspend fun getLike(): Result<BaseResponse<List<ResponseLikeDto>>> =
        kotlin.runCatching { contentDataSource.getLike() }

    override suspend fun getDetail(contentId: Int): Result<BaseResponse<ResponseGetContentDeatilDto>> =
        kotlin.runCatching { contentDataSource.getDetail(contentId) }

    override suspend fun getContent(): Result<BaseResponse<ResponseContentDto>> =
        kotlin.runCatching { contentDataSource.getContent() }

    override suspend fun postSave(
        requestPostSaveDto: RequestPostSaveDto
    ): Result<BaseResponse<Unit>> =
        kotlin.runCatching { contentDataSource.postSave(requestPostSaveDto) }
}
