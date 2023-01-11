package com.keyneez.presentation.main.home.recommend

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keyneez.data.model.request.RequestPostSaveDto
import com.keyneez.data.model.response.ResponseContentDto
import com.keyneez.data.repository.ContentRepository
import com.keyneez.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RecommendViewModel @Inject constructor(
    private val contentRepository: ContentRepository
) : ViewModel() {

    private val _stateMessage = MutableLiveData<UiState>()
    val stateMessage: LiveData<UiState>
        get() = _stateMessage

    private val _contentList = MutableLiveData<ResponseContentDto>()
    val contentList: LiveData<ResponseContentDto>
        get() = _contentList

    private val _saveState = MutableLiveData<Boolean>()
    val saveState: LiveData<Boolean>
        get() = _saveState

    init {
        getContent()
    }

    fun getContent() {
        viewModelScope.launch {
            contentRepository.getContent()
                .onSuccess { response ->
                    Timber.tag(successTag).d("response : $response")

                    if (response.data == null) {
                        Timber.d("GET CONTENT IS NULL")
                        _stateMessage.value = UiState.Failure(CONTENT_DATA_NULL_CODE)
                    }
                    _contentList.value = response.data!!
                    _stateMessage.value = UiState.Success
                }
                .onFailure {
                    Timber.tag(failTag).e("throwable : $it")
                    if (it is HttpException) {
                        Timber.tag(failTag).e("code : ${it.code()}")
                        Timber.tag(failTag).e("message : ${it.message()}")
                    }
                }
        }
    }

    fun postSave() {
        viewModelScope.launch {
            contentRepository.postSave(
                RequestPostSaveDto(key = 1, content = 1)
            )
                .onSuccess { response ->
                    Timber.tag(successTag).d("response : $response")
                    _saveState.value = true
                }.onFailure {
                    Timber.tag(failTag).e("throwable : $it")
                }
        }
    }

    companion object {
        const val CONTENT_DATA_NULL_CODE = 100

        private const val successTag = "GET_CONTENT_SUCCESS"
        private const val failTag = "GET_CONTENT_FAIL"
    }
}
