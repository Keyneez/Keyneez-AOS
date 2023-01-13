package com.keyneez.presentation.main.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keyneez.data.model.response.ResponseGetSearchDto
import com.keyneez.data.repository.ContentRepository
import com.keyneez.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val contentRepository: ContentRepository
) : ViewModel() {
    private val _searchList = MutableLiveData<List<ResponseGetSearchDto>>()
    val searchList: LiveData<List<ResponseGetSearchDto>>
        get() = _searchList

    private val _stateMessage = MutableLiveData<UiState>()
    val stateMessage: LiveData<UiState>
        get() = _stateMessage

    val key = MutableLiveData("")

    fun getSearchPostData() {
        // 코틀린은 자동적으로 타입을 추론해 주기 때문에 굳이 타입을 안 써줘도 된다.
        viewModelScope.launch {
            // 유저 키 받아오는 로직
            contentRepository.getSearch(key.toString()).onSuccess { response ->
                // 존재하지 않는 경우
                if (response.data == null) {
                    Timber.d("GET SEARCH LIST IS NULL")
                    _stateMessage.value = UiState.Failure(SEARCH_NULL_CODE)
                    return@onSuccess
                }
                Timber.d("GET SEARCH LIST SUCCESS")
                Timber.d("response : $response")

                _searchList.value = response.data!!
                _stateMessage.value = UiState.Success
            }.onFailure {
                Timber.e("GET SEARCH LIST SERVER ERROR")
                Timber.e("message : ${it.message}")
                if (it is HttpException) {
                    Timber.e("response : $it")
                    when (it.code()) {
                        SEARCH_NO_POST_CODE ->
                            _stateMessage.value =
                                UiState.Failure(SEARCH_NO_POST_CODE)
                        else -> _stateMessage.value = UiState.Error
                    }
                } else _stateMessage.value = UiState.Error
            }
        }
    }

    companion object {
        const val SEARCH_NULL_CODE = 100
        const val SEARCH_NO_POST_CODE = 404
    }
}
