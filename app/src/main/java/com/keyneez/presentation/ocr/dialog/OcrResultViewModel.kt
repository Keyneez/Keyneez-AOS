package com.keyneez.presentation.ocr.dialog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keyneez.data.model.request.RequestPostStudentUserCheckDto
import com.keyneez.data.model.request.RequestPostYouthUserCheckDto
import com.keyneez.data.repository.UserRepository
import com.keyneez.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class OcrResultViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    private val _stateMessage = MutableLiveData<UiState>()
    val stateMessage: LiveData<UiState>
        get() = _stateMessage

    fun postUserCheck(
        isStudent: Boolean,
        name: String,
        subEntry: String,
        img: String,
        isVertical: Boolean
    ) {
        // 학생증인 경우
        if (isStudent) {
            viewModelScope.launch {
                userRepository.postStudentUserCheck(
                    RequestPostStudentUserCheckDto(
                        subEntry,
                        name,
                        img,
                        isVertical
                    )
                )
                    .onSuccess { response ->
                        Timber.tag(successTag).d("response : $response")

                        if (response.data == null) {
                            _stateMessage.value = UiState.Failure(RESPONSE_NULL_CODE)
                            return@onSuccess
                        }

                        _stateMessage.value = UiState.Success
                    }
                    .onFailure {
                        Timber.tag(failTag).e("throwable: $it")
                        if (it is HttpException) {
                            Timber.tag(failTag).e("code : ${it.code()}")
                            Timber.tag(failTag).e("message : ${it.message()}")

                            when (it.code()) {
                                INVALID_TOKEN_CODE -> _stateMessage.value = UiState.Failure(
                                    INVALID_TOKEN_CODE
                                )
                                CHECK_FAIL_CODE -> _stateMessage.value = UiState.Failure(
                                    CHECK_FAIL_CODE
                                )
                                else -> _stateMessage.value = UiState.Error
                            }
                        } else _stateMessage.value = UiState.Error
                    }
            }
        }
        // 청소년증인 경우
        else {
            viewModelScope.launch {
                userRepository.postYouthUserCheck(RequestPostYouthUserCheckDto(name, subEntry, img, isVertical))
                    .onSuccess { response ->
                        Timber.tag(successTag).d("response : $response")

                        if (response.data == null) {
                            _stateMessage.value = UiState.Failure(RESPONSE_NULL_CODE)
                            return@onSuccess
                        }

                        _stateMessage.value = UiState.Success
                    }
                    .onFailure {
                        Timber.tag(failTag).e("throwable: $it")
                        if (it is HttpException) {
                            Timber.tag(failTag).e("code : ${it.code()}")
                            Timber.tag(failTag).e("message : ${it.message()}")

                            when (it.code()) {
                                INVALID_TOKEN_CODE -> _stateMessage.value = UiState.Failure(
                                    INVALID_TOKEN_CODE
                                )
                                CHECK_FAIL_CODE -> _stateMessage.value = UiState.Failure(
                                    CHECK_FAIL_CODE
                                )
                                else -> _stateMessage.value = UiState.Error
                            }
                        } else _stateMessage.value = UiState.Error
                    }
            }
        }
    }

    companion object {
        const val RESPONSE_NULL_CODE = 100
        const val INVALID_TOKEN_CODE = 401
        const val CHECK_FAIL_CODE = 400

        private const val successTag = "POST_CHECK_USER_SUCCESS"
        private const val failTag = "POST_CHECK_USER_FAIL"
    }
}
