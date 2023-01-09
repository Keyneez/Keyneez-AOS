package com.keyneez.presentation.signup.interest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.keyneez.util.extension.notifyObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InterestViewModel @Inject constructor() : ViewModel() {
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

    companion object {
        private const val INTEREST_SELECTION_MAX = 3
    }
}
