package com.keyneez.presentation.main.id

import androidx.lifecycle.ViewModel
import com.keyneez.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IdViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel()
