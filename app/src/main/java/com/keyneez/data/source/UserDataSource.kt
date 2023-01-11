package com.keyneez.data.source

import com.keyneez.data.model.request.RequestPostDanalSignupDto
import com.keyneez.data.model.response.ResponseIdDto
import com.keyneez.data.model.response.ResponsePostDanalSignupDto
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
    suspend fun getId(): BaseResponse<ResponseIdDto> =
        userService.getId()
}
