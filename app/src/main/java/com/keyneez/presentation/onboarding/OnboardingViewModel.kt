package com.keyneez.presentation.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor() : ViewModel() {
    private val _position: MutableLiveData<Int> = MutableLiveData()
    val positon: LiveData<Int>
        get() = _position

    fun setPosition(position: Int) {
        _position.value = position
    }
}
