package com.keyneez.data.source

import com.keyneez.data.model.request.*
import com.keyneez.data.model.response.*
import com.keyneez.data.model.response.wrapper.BaseResponse
import com.keyneez.data.service.UserService
import javax.inject.Inject

class UserDataSource @Inject constructor(
    private val userService: UserService
) {
    suspend fun postDanalSignup(
        requestPostDanalSignupDto: RequestPostDanalSignupDto
    ): BaseResponse<ResponsePostDanalSignupDto> =
        userService.postDanalSignup(requestPostDanalSignupDto)

    suspend fun postUserLogIn(
        requestPostUserLogInDto: RequestPostUserLogInDto
    ): BaseResponse<ResponsePostUserLogInDto> =
        userService.postUserLogIn(requestPostUserLogInDto)

    suspend fun getId(): BaseResponse<ResponseIdDto> = userService.getId()

    suspend fun patchUserTypeSignup(
        requestPatchUserTypeDto: RequestPatchUserTypeDto
    ): BaseResponse<ResponsePatchUserTypeDto> =
        userService.patchUserTypeSignup(requestPatchUserTypeDto)

    suspend fun patchPwdSignup(
        requestPatchPwdSignupDto: RequestPatchPwdSignupDto
    ): BaseResponse<ResponsePatchPwdSignupDto> =
        userService.patchPwdSignup(requestPatchPwdSignupDto)

    suspend fun postPwdCheck(
        requestPostPwdCheckDto: RequestPostPwdCheckDto
    ): Unit = userService.postPwdCheck(requestPostPwdCheckDto)

    suspend fun postStudentUserCheck(
        requestPostStudentUserCheckDto: RequestPostStudentUserCheckDto
    ): BaseResponse<ResponsePostUserCheckDto> =
        userService.postCheckStudentUser(requestPostStudentUserCheckDto)

    suspend fun postYouthUserCheck(
        requestPostYouthUserCheckDto: RequestPostYouthUserCheckDto
    ): BaseResponse<ResponsePostUserCheckDto> =
        userService.postCheckYouthUser(requestPostYouthUserCheckDto)
}
