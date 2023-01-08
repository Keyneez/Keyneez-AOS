package com.keyneez.util.extension // ktlint-disable filename

import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<T>.notifyObserver() {
    this.value = this.value
}
