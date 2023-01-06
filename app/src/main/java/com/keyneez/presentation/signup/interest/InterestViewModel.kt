package com.keyneez.presentation.signup.interest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InterestViewModel @Inject constructor() : ViewModel() {
    private val _selectedInterests = MutableLiveData<LinkedHashSet<String>>()
    val selectedInterests: LiveData<LinkedHashSet<String>>
        get() = _selectedInterests

    /** 관심사 선택 */
    fun selectInterest(hashTag: String) {
        // 초기화되지 않은 경우
        if (_selectedInterests.value == null) _selectedInterests.value = LinkedHashSet()
        val interest = hashTag.substring(1, hashTag.length)

        // 관심사 제거
        if (_selectedInterests.value!!.contains(interest)) _selectedInterests.value!!.remove(
            interest
        )

        // 이미 3개의 관심사를 선택한 경우
        if (_selectedInterests.value!!.size >= 3) return

        // 관심사 추가
        _selectedInterests.value!!.add(interest)
    }
}
