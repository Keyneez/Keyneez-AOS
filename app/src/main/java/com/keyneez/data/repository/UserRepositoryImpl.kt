package com.keyneez.data.repository

import com.keyneez.data.model.request.RequestPostDanalSignupDto
import com.keyneez.data.model.response.ResponseIdDto
import com.keyneez.data.model.response.ResponsePostDanalSignupDto
import com.keyneez.data.model.response.wrapper.BaseResponse
import com.keyneez.data.source.UserDataSource
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
) : UserRepository {
    override suspend fun postDanalSignup(
        requestPostDanalSignupDto: RequestPostDanalSignupDto
    ): Result<BaseResponse<ResponsePostDanalSignupDto>> =
        kotlin.runCatching { userDataSource.postDanalSignup(requestPostDanalSignupDto) }

    override suspend fun getId(): Result<BaseResponse<ResponseIdDto>> =
        kotlin.runCatching { userDataSource.getId() }
}
