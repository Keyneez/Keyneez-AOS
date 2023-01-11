package com.keyneez.data.service

import com.keyneez.data.model.request.RequestPatchUserTypeDto
import com.keyneez.data.model.request.RequestPostDanalSignupDto
import com.keyneez.data.model.response.ResponsePatchUserTypeDto
import com.keyneez.data.model.response.ResponsePostDanalSignupDto
import com.keyneez.data.model.response.wrapper.BaseResponse
import retrofit2.http.Body
import retrofit2.http.PATCH
import retrofit2.http.POST

interface UserService {
    // 유저 생성 (다날 정보)
    @POST("user/signup")
    suspend fun postDanalSignup(
        @Body request: RequestPostDanalSignupDto
    ): BaseResponse<ResponsePostDanalSignupDto>

    // 유저 생성 (성향, 관심사)
    @PATCH("user/signup")
    suspend fun patchUserTypeSignup(
        @Body request: RequestPatchUserTypeDto
    ): BaseResponse<ResponsePatchUserTypeDto>
}
