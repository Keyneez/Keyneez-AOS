package com.keyneez.data.repository

import com.keyneez.data.model.response.ResponseLikeDto
import com.keyneez.data.model.response.wrapper.BaseResponse
import com.keyneez.data.source.ContentDataSource
import javax.inject.Inject

class ContentRepositoryImpl @Inject constructor(
    private val contentDataSource: ContentDataSource
) : ContentRepository {
    override suspend fun getLike(): Result<BaseResponse<List<ResponseLikeDto>>> =
        kotlin.runCatching { contentDataSource.getLike() }
}
