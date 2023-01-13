package com.keyneez.presentation.main.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keyneez.data.model.response.ResponseGetContentDeatilDto
import com.keyneez.data.repository.ContentRepository
import com.keyneez.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val contentRepository: ContentRepository
) : ViewModel() {

    private val _detailContent = MutableLiveData<ResponseGetContentDeatilDto>()
    val detailContent: LiveData<ResponseGetContentDeatilDto>
        get() = _detailContent

    private val _stateMessage = MutableLiveData<UiState>()
    val stateMessage: LiveData<UiState>
        get() = _stateMessage

    private val _saveState = MutableLiveData<Boolean>()
    val saveState: LiveData<Boolean>
        get() = _saveState

    fun getDetail(contentId: Int) {
        viewModelScope.launch {
            contentRepository.getDetail(contentId)
                .onSuccess { response ->
                    if (response.data == null) {
                        Timber.d("GET DETAIL LIST IS NULL")
                        _stateMessage.value = UiState.Failure(100)
                        return@onSuccess
                    }
                    Timber.d("GET DETAIL LIST SUCCESS")
                    Timber.d("response : $response")
                    _detailContent.value = response.data!!
                    _stateMessage.value = UiState.Success
                }.onFailure { throwable ->
                    Timber.e("$throwable")
                }
        }
    }
}
