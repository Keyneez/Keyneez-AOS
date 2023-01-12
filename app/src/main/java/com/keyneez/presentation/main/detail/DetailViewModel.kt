package com.keyneez.presentation.main.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keyneez.data.model.response.ResponseGetContentDeatilDto
import com.keyneez.data.repository.ContentRepository
import com.keyneez.util.UiState
import kotlinx.coroutines.launch
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val repository: ContentRepository
) : ViewModel() {

    private val _detailContent = MutableLiveData<ResponseGetContentDeatilDto>()
    val detailContent: LiveData<ResponseGetContentDeatilDto>
        get() = _detailContent

    private val _stateMessage = MutableLiveData<UiState>()
    val stateMessage: LiveData<UiState>
        get() = _stateMessage

    val contentId = MutableLiveData<Int>()

    private fun initContentId(id: Int) {
        contentId.value = id
    }

    private fun getDetail(contentId: Int) {
        viewModelScope.launch {
            ContentRepository.getDetail(contentId).onSuccess { response ->
                if (response.data == null) {
                    Timber.d("GET DETAIL LIST IS NULL")
                    _stateMessage.value = UiState.Failure(100)
                    return@onSuccess
                }
                Timber.d("GET DETAIL LIST SUCCESS")
                Timber.d("response : $response")
                _detailContent.value = response.data!!
                _stateMessage.value = UiState.Success
            }.onFailure {
                Timber.e("GET DETAIL LIST SERVER ERROR")
                Timber.e("message : $message")
                if (it is HttpException) {
                    Timber.e("response : $it")
                    when (it.code()) {
                        404 ->
                            _stateMessage.value =
                                UiState.Failure(404)
                        else -> _stateMessage.value = UiState.Error
                    }
                } else _stateMessage.value = UiState.Error
            }
        }
    }
}
