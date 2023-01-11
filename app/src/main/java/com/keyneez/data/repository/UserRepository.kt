package com.keyneez.data.repository

import com.keyneez.data.model.request.RequestPostDanalSignupDto
import com.keyneez.data.model.response.ResponseIdDto
import com.keyneez.data.model.response.ResponsePostDanalSignupDto
import com.keyneez.data.model.response.wrapper.BaseResponse

interface UserRepository {
    suspend fun postDanalSignup(
        requestPostDanalSignupDto: RequestPostDanalSignupDto
    ): Result<BaseResponse<ResponsePostDanalSignupDto>>
    suspend fun getId(): Result<BaseResponse<ResponseIdDto>>
}
