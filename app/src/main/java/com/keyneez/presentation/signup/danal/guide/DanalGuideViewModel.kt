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
        Timber.e("CLICK!!!!!! CLICK!!!!!!")
        val name = "내가일빠"
        val birth = "000101"
        val gender = "female"
        val tel = "SKT"
        val phone = "010-0000-0000"

        viewModelScope.launch {
            userRepository.postDanalSignup(
                RequestPostDanalSignupDto(name, birth, gender, tel, phone)
            )
                .onSuccess { response ->
                    Timber.tag(successTag).d("response : $response")

                    when (response.status) {
                        USER_DATA_NULL_CODE -> {
                            _postDanalSignupState.value = UiState.Failure(USER_DATA_NULL_CODE)
                        }
                        EXISTENTIAL_USER_CODE -> {
                            _postDanalSignupState.value = UiState.Failure(EXISTENTIAL_USER_CODE)
                        }
                        else -> {
                            _postDanalSignupState.value = UiState.Success
                            _userData.value = response.data!!
                        }
                    }
                }
                .onFailure {
                    if (it is HttpException) {
                        Timber.tag(failTag).e("code : ${it.code()}")
                        Timber.tag(failTag).e("message : ${it.message()}")

                        _postDanalSignupState.value = UiState.Error
                    }
                }
        }
    }

    companion object {
        const val USER_DATA_NULL_CODE = 100
        const val EXISTENTIAL_USER_CODE = 202

        private const val successTag = "POST_DANAL_SIGNUP_SUCCESS"
        private const val failTag = "POST_DANAL_SIGNUP_FAIL"
    }
}
