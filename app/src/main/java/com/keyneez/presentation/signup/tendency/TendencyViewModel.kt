package com.keyneez.presentation.signup.tendency

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TendencyViewModel @Inject constructor() : ViewModel() {
    private val _selectedTendency = MutableLiveData<String>()
    val selectedItem: LiveData<String>
        get() = _selectedTendency

    /** 사용자 성향 선택 */
    fun selectTendency(tendency: String) {
        _selectedTendency.value = tendency
    }
}
