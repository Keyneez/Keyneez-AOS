package com.keyneez.data.repository

import com.keyneez.data.model.request.RequestPatchPwdSignupDto
import com.keyneez.data.model.request.RequestPatchUserTypeDto
import com.keyneez.data.model.request.RequestPostDanalSignupDto
import com.keyneez.data.model.request.RequestPostPwdCheckDto
import com.keyneez.data.model.response.ResponseIdDto
import com.keyneez.data.model.response.ResponsePatchPwdSignupDto
import com.keyneez.data.model.response.ResponsePatchUserTypeDto
import com.keyneez.data.model.response.ResponsePostDanalSignupDto
import com.keyneez.data.model.response.wrapper.BaseResponse
import com.keyneez.data.source.LocalPrefDataSource
import com.keyneez.data.source.UserDataSource
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource,
    private val localPrefDataSource: LocalPrefDataSource
) : UserRepository {
    override fun setUserName(name: String) {
        localPrefDataSource.setUserName(name)
    }

    override fun getUserName(): String? = localPrefDataSource.getUserName()

    override fun setAccessToken(accessToken: String) {
        localPrefDataSource.setAccessToken(accessToken)
    }

    override fun getAccessToken(): String? = localPrefDataSource.getAccessToken()

    override suspend fun postDanalSignup(
        requestPostDanalSignupDto: RequestPostDanalSignupDto
    ): Result<BaseResponse<ResponsePostDanalSignupDto>> =
        kotlin.runCatching { userDataSource.postDanalSignup(requestPostDanalSignupDto) }

    override suspend fun getId(): Result<BaseResponse<ResponseIdDto>> =
        kotlin.runCatching { userDataSource.getId() }

    override suspend fun patchUserTypeSignup(
        requestPatchUserTypeDto: RequestPatchUserTypeDto
    ): Result<BaseResponse<ResponsePatchUserTypeDto>> =
        kotlin.runCatching { userDataSource.patchUserTypeSignup(requestPatchUserTypeDto) }

    override suspend fun patchPwdSignup(
        requestPatchPwdSignupDto: RequestPatchPwdSignupDto
    ): Result<BaseResponse<ResponsePatchPwdSignupDto>> =
        kotlin.runCatching { userDataSource.patchPwdSignup(requestPatchPwdSignupDto) }

    override suspend fun postPwdCheck(
        requestPostPwdCheckDto: RequestPostPwdCheckDto
    ): Result<Unit> =
        kotlin.runCatching { userDataSource.postPwdCheck(requestPostPwdCheckDto) }
}
