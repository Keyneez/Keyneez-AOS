package com.keyneez.presentation.login.pin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keyneez.data.model.request.RequestPostUserLogInDto
import com.keyneez.data.repository.UserRepository
import com.keyneez.data.source.LocalPrefDataSource
import com.keyneez.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginPinViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val localPrefDataSource: LocalPrefDataSource
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

    /** 서버에 로그인 요청 */
    fun postUserLogin(phone: String, pwd: String) {
        viewModelScope.launch {
            userRepository.postUserLogIn(RequestPostUserLogInDto(phone, pwd))
                .onSuccess { response ->
                    Timber.tag(successTag).d("response : $response")

                    if (response.data == null) {
                        _stateMessage.value = UiState.Failure(RESPONSE_NULL_CODE)
                        return@onSuccess
                    }

                    localPrefDataSource.setAccessToken(response.data.accessToken)
                    _stateMessage.value = UiState.Success
                }
                .onFailure {
                    Timber.tag(failTag).e("throwable: $it")
                    if (it is HttpException) {
                        Timber.tag(failTag).e("code : ${it.code()}")
                        Timber.tag(failTag).e("message : ${it.message()}")

                        when (it.code()) {
                            INVALID_PWD_CODE ->
                                _stateMessage.value =
                                    UiState.Failure(INVALID_PWD_CODE)
                            UNAUTHORIZED_USER_CODE -> _stateMessage.value = UiState.Failure(
                                UNAUTHORIZED_USER_CODE
                            )
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
        const val RESPONSE_NULL_CODE = 100
        const val UNAUTHORIZED_USER_CODE = 401
        const val INVALID_PWD_CODE = 404

        private const val successTag = "POST_LOGIN_SUCCESS"
        private const val failTag = "POST_LOGIN_FAIL"
    }
}
