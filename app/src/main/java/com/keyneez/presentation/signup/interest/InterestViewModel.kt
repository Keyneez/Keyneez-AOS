package com.keyneez.presentation.signup.interest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
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
    fun selectInterest(hashTag: String) {
        val interest = hashTag.substring(1, hashTag.length)
        Timber.d("Interest 선택 $hashTag -> $interest")
        Timber.d("Interest Hash Set : ${_selectedInterests.value}")

        // 관심사 제거
        if (_selectedInterests.value!!.contains(interest)) {
            _selectedInterests.value!!.remove(interest)
            return
        }

        // 이미 3개의 관심사를 선택한 경우
        if (_selectedInterests.value!!.size >= INTEREST_SELECTION_MAX) return

        // 관심사 추가
        _selectedInterests.value!!.add(interest)
    }

    companion object {
        const val INTEREST_SELECTION_MAX = 3
    }
}
