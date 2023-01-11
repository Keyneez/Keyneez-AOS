package com.keyneez.data.repository

import com.keyneez.data.model.request.RequestPostDanalSignupDto
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
}
