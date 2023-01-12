package com.keyneez.presentation.signup.pin.confirm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keyneez.data.model.request.RequestPostPwdCheckDto
import com.keyneez.data.repository.UserRepository
import com.keyneez.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SignupPinConfirmViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    private val _stateMessage = MutableLiveData<UiState>()
    val stateMessage: LiveData<UiState>
        get() = _stateMessage

    private val _passwordText = MutableLiveData("")
    val passwordText: LiveData<String>
        get() = _passwordText

    private val _keypadList = MutableLiveData<List<Int>>()
    val keypadList: LiveData<List<Int>>
        get() = _keypadList

    init {
        rearrangeKeypad()
    }

    fun postPwdCheck(pwd: String) {
        viewModelScope.launch {
            userRepository.postPwdCheck(RequestPostPwdCheckDto(pwd))
                .onSuccess { response ->
                    Timber.tag(successTag).d("response : $response")

                    _stateMessage.value = UiState.Success
                }
                .onFailure {
                    Timber.tag(failTag).e("throwable : $it")
                    if (it is HttpException) {
                        Timber.tag(failTag).e("code : ${it.code()}")
                        Timber.tag(failTag).e("message : ${it.message()}")

                        when (it.code()) {
                            INVALID_PWD_CODE ->
                                _stateMessage.value =
                                    UiState.Failure(INVALID_PWD_CODE)
                            else -> _stateMessage.value = UiState.Error
                        }
                    } else _stateMessage.value = UiState.Error
                }
        }
    }

    /** 숫자 재배열 */
    fun rearrangeKeypad() {
        val numbers = linkedSetOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
        val tempKeypadList = mutableListOf(-1, -1, -1, -1, -1, -1, -1, -1, -1, -1)

        for (i in 9 downTo 0) {
            val randomNum = numbers.random()
            tempKeypadList[i] = randomNum
            numbers.remove(randomNum)
        }

        _keypadList.value = tempKeypadList
    }

    /** 서버에 비밀번호 대조 요청 */
    fun patchPwdSignup() {
    }

    /** 비밀번호 입력 */
    fun editPassword(number: Int) {
        _passwordText.value += _keypadList.value?.get(number)
    }

    /** 비밀번호 지우기 */
    fun erasePassword() {
        if (_passwordText.value?.isEmpty() == true) return
        _passwordText.value =
            _passwordText.value?.length?.let { _passwordText.value?.substring(0, it.minus(1)) }
    }

    /** 비밀번호 초기화 */
    fun resetPassword() {
        rearrangeKeypad()
        _passwordText.value = ""
    }

    companion object {
        const val INVALID_PWD_CODE = 400

        private const val successTag = "POST_PWD_CHECK_SUCCESS"
        private const val failTag = "POST_PWD_CHECK_FAIL"
    }
}
