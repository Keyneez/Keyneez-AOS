package com.keyneez.presentation.main.id.dialog

import androidx.lifecycle.ViewModel
import com.keyneez.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IdProfileViewModel @Inject constructor(private val userRepository: UserRepository) :
    ViewModel()
