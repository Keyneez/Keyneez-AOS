package com.keyneez.presentation.main.like

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keyneez.data.model.response.ResponseLikeDto
import com.keyneez.util.UiState
import kotlinx.coroutines.launch
import timber.log.Timber

class LikeViewModel : ViewModel() {
    private val _likeList = MutableLiveData<List<ResponseLikeDto>>()
    val likeList: LiveData<List<ResponseLikeDto>>
        get() = _likeList

    private val _stateMessage = MutableLiveData<UiState>()
    val stateMessage: LiveData<UiState>
        get() = _stateMessage

    init {
        getLikeList()
    }

    private fun getLikeList() {
        // 코틀린은 자동적으로 타입을 추론해 주기 때문에 굳이 타입을 안 써줘도 된다.
        viewModelScope.launch {
            likeRepository.getMusicList()
                .onSuccess { response ->
                    // 음악이 존재하지 않는 경우
                    if (response.data == null) {
                        Timber.d("GET LIKE LIST IS NULL")
                        _stateMessage.value = UiState.Failure(LIKE_NULL_CODE)
                        return@onSuccess
                    }

                    Timber.d("GET LIKE LIST SUCCESS")
                    Timber.d("response : $response")
                    _likeList.value = response.data!!
                    _stateMessage.value = UiState.Success
                }
                .onFailure {
                    Timber.e("GET LIKE LIST SERVER ERROR")
                    Timber.e("message : ${it.message}")
                    _stateMessage.value = UiState.Error
                }
        }
    }
    companion object {
        const val LIKE_NULL_CODE = 100
    }
}
