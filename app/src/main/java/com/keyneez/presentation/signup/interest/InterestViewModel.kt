package com.keyneez.presentation.signup.interest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keyneez.data.model.request.RequestPatchUserTypeDto
import com.keyneez.data.model.response.ResponsePatchUserTypeDto
import com.keyneez.data.repository.UserRepository
import com.keyneez.util.UiState
import com.keyneez.util.extension.notifyObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class InterestViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    private val _stateMessage = MutableLiveData<UiState>()
    val stateMessage: LiveData<UiState>
        get() = _stateMessage

    private val _userData = MutableLiveData<ResponsePatchUserTypeDto>()
    val userData: LiveData<ResponsePatchUserTypeDto>
        get() = _userData

    private val _selectedInterests = MutableLiveData<LinkedHashSet<String>>()
    val selectedInterests: LiveData<LinkedHashSet<String>>
        get() = _selectedInterests

    init {
        _selectedInterests.value = LinkedHashSet()
    }

    /** 관심사 선택 */
    fun selectInterest(interest: String) {
        // 이미 선택된 경우 관심사 제거
        if (_selectedInterests.value!!.contains(interest)) {
            _selectedInterests.value!!.remove(interest)
            _selectedInterests.notifyObserver()
            return
        }

        // 이미 3개의 관심사를 선택한 경우 무시
        if (_selectedInterests.value!!.size >= INTEREST_SELECTION_MAX) return

        // 관심사 추가
        _selectedInterests.value!!.add(interest)
        _selectedInterests.notifyObserver()
    }

    /** 서버에 성향, 관심사 기반 유저 생성 요청 */
    fun patchUserTypeSignup(disposition: String?) {
        var tempInterestList: List<String>? = _selectedInterests.value?.toList() ?: return
        val interestList = listOf(
            tempInterestList!![0].substring(1, tempInterestList[0].length),
            tempInterestList!![1].substring(1, tempInterestList[1].length),
            tempInterestList!![2].substring(1, tempInterestList[2].length)
        )

        viewModelScope.launch {
            userRepository.patchUserTypeSignup(
                RequestPatchUserTypeDto(
                    disposition.toString(),
                    interestList
                )
            )
                .onSuccess { response ->
                    Timber.tag(successTag).d("response: $response")

                    if (response.data == null) {
                        _stateMessage.value = UiState.Failure(RESPONSE_NULL_CODE)
                        return@onSuccess
                    }

                    _userData.value = response.data!!
                    _stateMessage.value = UiState.Success
                }
                .onFailure {
                    Timber.tag(failTag).d("throwable : $it")
                    if (it is HttpException) {
                        Timber.tag(failTag).e("code : ${it.code()}")
                        Timber.tag(failTag).e("message : ${it.message()}")
                    }
                    _stateMessage.value = UiState.Error
                }
        }
    }

    companion object {
        const val RESPONSE_NULL_CODE = 100

        private const val INTEREST_SELECTION_MAX = 3

        private const val successTag = "PATCH_USER_TYPE_SUCCESS"
        private const val failTag = "PATCH_USER_TYPE_FAIL"
    }
}
