package com.keyneez.data.service

import com.keyneez.data.model.request.RequestPatchPwdSignupDto
import com.keyneez.data.model.request.RequestPatchUserTypeDto
import com.keyneez.data.model.request.RequestPostDanalSignupDto
import com.keyneez.data.model.request.RequestPostPwdCheckDto
import com.keyneez.data.model.response.ResponseIdDto
import com.keyneez.data.model.response.ResponsePatchPwdSignupDto
import com.keyneez.data.model.response.ResponsePatchUserTypeDto
import com.keyneez.data.model.response.ResponsePostDanalSignupDto
import com.keyneez.data.model.response.wrapper.BaseResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST

interface UserService {
    // 유저 생성 (다날 정보)
    @POST("user/signup")
    suspend fun postDanalSignup(
        @Body request: RequestPostDanalSignupDto
    ): BaseResponse<ResponsePostDanalSignupDto>

    @GET("user")
    suspend fun getId(): BaseResponse<ResponseIdDto>

    // 유저 생성 (성향, 관심사)
    @PATCH("user/signup")
    suspend fun patchUserTypeSignup(
        @Body request: RequestPatchUserTypeDto
    ): BaseResponse<ResponsePatchUserTypeDto>

    // 유저 생성 (비밀번호)
    @PATCH("user/signup/pw")
    suspend fun patchPwdSignup(
        @Body request: RequestPatchPwdSignupDto
    ): BaseResponse<ResponsePatchPwdSignupDto>

    // 비밀번호 대조
    @POST("user/signup/pw")
    suspend fun postPwdCheck(
        @Body request: RequestPostPwdCheckDto
    )
}
