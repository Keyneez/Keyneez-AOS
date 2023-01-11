package com.keyneez.presentation.signup.danal.guide

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keyneez.data.model.request.RequestPostDanalSignupDto
import com.keyneez.data.model.response.ResponsePostDanalSignupDto
import com.keyneez.data.repository.UserRepository
import com.keyneez.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DanalGuideViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    private val _postDanalSignupState = MutableLiveData<UiState>()
    val postDanalSignupState: LiveData<UiState>
        get() = _postDanalSignupState

    private val _userData = MutableLiveData<ResponsePostDanalSignupDto>()
    val userData: LiveData<ResponsePostDanalSignupDto>
        get() = _userData

    /** 서버에 다날 정보 관련 유저 생성 요청 */
    fun postDanalSignup() {
        val name = "테스터"
        val birth = "000101"
        val gender = "female"
        val phone = "010-1000-0001"

        viewModelScope.launch {
            userRepository.postDanalSignup(
                RequestPostDanalSignupDto(name, birth, gender, phone)
            )
                .onSuccess { response ->
                    Timber.tag(successTag).d("response : $response")

                    // 이미 존재하는 회원인 경우
                    if (response.status == EXISTENTIAL_USER_CODE) {
                        _postDanalSignupState.value = UiState.Failure(EXISTENTIAL_USER_CODE)
                        return@onSuccess
                    }

                    // 반환된 값이 null 인 경우
                    if (response.data == null) {
                        _postDanalSignupState.value = UiState.Failure(USER_DATA_NULL_CODE)
                        return@onSuccess
                    }

                    // danal signup success
                    setUserName(response.data.name)
                    setAccessToken(response.data.accessToken)
                    _postDanalSignupState.value = UiState.Success
                    _userData.value = response.data!!
                }
                .onFailure {
                    Timber.tag(failTag).e("throwable : $it")
                    if (it is HttpException) {
                        Timber.tag(failTag).e("code : ${it.code()}")
                        Timber.tag(failTag).e("message : ${it.message()}")
                    }
                    _postDanalSignupState.value = UiState.Error
                }
        }
    }

    private fun setUserName(name: String) {
        userRepository.setUserName(name)
    }

    private fun setAccessToken(accessToken: String) {
        userRepository.setAccessToken(accessToken)
    }

    companion object {
        const val USER_DATA_NULL_CODE = 100
        const val EXISTENTIAL_USER_CODE = 202

        private const val successTag = "POST_DANAL_SIGNUP_SUCCESS"
        private const val failTag = "POST_DANAL_SIGNUP_FAIL"
    }
}
