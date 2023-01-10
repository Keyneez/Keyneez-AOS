package com.keyneez.presentation.main.like

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.keyneez.data.entity.LikeData
import com.lab.keyneez.R
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LikeViewModel @Inject constructor() : ViewModel() {
    private val _likeList = MutableLiveData<List<LikeData>>()
    val likeList: LiveData<List<LikeData>>
        get() = _likeList

    init {
        getLikeList()
    }

    private fun getLikeList() {
        // 코틀린은 자동적으로 타입을 추론해 주기 때문에 굳이 타입을 안 써줘도 된다.
        val LikeDatas = listOf(
            LikeData(
                background = R.drawable.img_like_background,
                date = "12.4-12-10",
                title = "청소년\n영화관 할인"
            ),
            LikeData(
                background = R.drawable.img_like_background,
                date = "12.11-12.17",
                title = "청소년\n미술관 할인"
            ),
            LikeData(
                background = R.drawable.img_like_background,
                date = "12.18-12.24",
                title = "청소년\n공연장 할인"
            ),
            LikeData(
                background = R.drawable.img_like_background,
                date = "12.25-12.31",
                title = "청소년\n박물관 할인"
            ),
            LikeData(
                background = R.drawable.img_like_background,
                date = "1.1-1.7",
                title = "청소년\n서점 할인"
            ),
            LikeData(
                background = R.drawable.img_like_background,
                date = "1.8-1.14",
                title = "청소년\n강의 할인"
            )
        )
        _likeList.value = LikeDatas
    }
}
