package com.keyneez.data.source

import com.keyneez.data.model.response.ResponseGetContentDeatilDto
import com.keyneez.data.model.response.ResponseLikeDto
import com.keyneez.data.model.response.wrapper.BaseResponse
import com.keyneez.data.service.ContentService
import javax.inject.Inject

class ContentDataSource @Inject constructor(
    private val contentService: ContentService
) {

    suspend fun getLike(): BaseResponse<List<ResponseLikeDto>> = contentService.getLike()

    suspend fun getDetail(contentId: Int): BaseResponse<ResponseGetContentDeatilDto> =
        contentService.getDetail(contentId)
}
