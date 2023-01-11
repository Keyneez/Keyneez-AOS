package com.keyneez.presentation.main.id

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keyneez.data.model.response.ResponseIdDto
import com.keyneez.data.repository.UserRepository
import com.keyneez.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class IdViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    private val _userData = MutableLiveData<ResponseIdDto>()
    val userData: LiveData<ResponseIdDto>
        get() = _userData

    init {
        getIdData()
    }

    private val _stateMessage = MutableLiveData<UiState>()
    val stateMessage: LiveData<UiState>
        get() = _stateMessage

    private fun getIdData() {
        // 코틀린은 자동적으로 타입을 추론해 주기 때문에 굳이 타입을 안 써줘도 된다.
        viewModelScope.launch {
            // 유저 키 받아오는 로직
            userRepository.getId().onSuccess { response ->
                // 존재하지 않는 경우
                if (response.data == null) {
                    Timber.d("GET ID DATA IS NULL")
                    _stateMessage.value = UiState.Failure(ID_NULL_CODE)
                    return@onSuccess
                }
                Timber.d("GET ID DATA SUCCESS")
                Timber.d("response : $response")
                _userData.value = response.data!!
                _stateMessage.value = UiState.Success
            }.onFailure {
                Timber.e("GET ID DATA SERVER ERROR")
                Timber.e("message : ${it.message}")
                if (it is HttpException) {
                    Timber.e("response : $it")
                    when (it.code()) {
                        IdViewModel.ID_INVALID_TOKEN_CODE ->
                            _stateMessage.value =
                                UiState.Failure(ID_INVALID_TOKEN_CODE)
                        else -> _stateMessage.value = UiState.Error
                    }
                } else _stateMessage.value = UiState.Error
            }
        }
    }

    companion object {
        const val ID_NULL_CODE = 100
        const val ID_INVALID_TOKEN_CODE = 401
    }
}
