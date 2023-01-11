package com.keyneez.data.service

import com.keyneez.data.model.request.RequestPostDanalSignupDto
import com.keyneez.data.model.response.ResponsePostDanalSignupDto
import com.keyneez.data.model.response.wrapper.BaseResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    // 유저 생성 (다날 정보)
    @POST("user/signup")
    suspend fun postDanalSignup(
        @Body request: RequestPostDanalSignupDto
    ): BaseResponse<ResponsePostDanalSignupDto>
}
