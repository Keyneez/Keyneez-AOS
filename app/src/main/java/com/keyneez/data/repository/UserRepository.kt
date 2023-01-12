package com.keyneez.data.repository

import com.keyneez.data.model.request.RequestPatchPwdSignupDto
import com.keyneez.data.model.request.RequestPatchUserTypeDto
import com.keyneez.data.model.request.RequestPostDanalSignupDto
import com.keyneez.data.model.request.RequestPostPwdCheckDto
import com.keyneez.data.model.response.ResponsePatchPwdSignupDto
import com.keyneez.data.model.response.ResponsePatchUserTypeDto
import com.keyneez.data.model.response.ResponsePostDanalSignupDto
import com.keyneez.data.model.response.wrapper.BaseResponse

interface UserRepository {
    // shared preferences
    fun setUserName(name: String)
    fun getUserName(): String?

    fun setAccessToken(accessToken: String)
    fun getAccessToken(): String?

    suspend fun postDanalSignup(
        requestPostDanalSignupDto: RequestPostDanalSignupDto
    ): Result<BaseResponse<ResponsePostDanalSignupDto>>

    suspend fun patchUserTypeSignup(
        requestPatchUserTypeDto: RequestPatchUserTypeDto
    ): Result<BaseResponse<ResponsePatchUserTypeDto>>

    suspend fun patchPwdSignup(
        requestPatchPwdSignupDto: RequestPatchPwdSignupDto
    ): Result<BaseResponse<ResponsePatchPwdSignupDto>>

    suspend fun postPwdCheck(
        requestPostPwdCheckDto: RequestPostPwdCheckDto
    ): Result<Unit>
}
