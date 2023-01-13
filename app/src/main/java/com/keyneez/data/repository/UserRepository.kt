package com.keyneez.data.repository

import com.keyneez.data.model.request.*
import com.keyneez.data.model.response.*
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

    suspend fun postUserLogIn(
        requestPostUserLogInDto: RequestPostUserLogInDto
    ): Result<BaseResponse<ResponsePostUserLogInDto>>

    suspend fun getId(): Result<BaseResponse<ResponseIdDto>>

    suspend fun patchUserTypeSignup(
        requestPatchUserTypeDto: RequestPatchUserTypeDto
    ): Result<BaseResponse<ResponsePatchUserTypeDto>>

    suspend fun patchPwdSignup(
        requestPatchPwdSignupDto: RequestPatchPwdSignupDto
    ): Result<BaseResponse<ResponsePatchPwdSignupDto>>

    suspend fun postPwdCheck(
        requestPostPwdCheckDto: RequestPostPwdCheckDto
    ): Result<Unit>

    suspend fun postStudentUserCheck(
        requestPostStudentUserCheckDto: RequestPostStudentUserCheckDto
    ): Result<BaseResponse<ResponsePostUserCheckDto>>

    suspend fun postYouthUserCheck(
        requestPostYouthUserCheckDto: RequestPostYouthUserCheckDto
    ): Result<BaseResponse<ResponsePostUserCheckDto>>
}
