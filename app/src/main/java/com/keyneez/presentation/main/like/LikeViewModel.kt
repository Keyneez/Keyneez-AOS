package com.keyneez.presentation.main.like

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.keyneez.data.entity.LikeData
import com.lab.keyneez.R

class LikeViewModel : ViewModel() {
    // binding하는 방식으로 선언해 주기!
    private val _likeList = MutableLiveData<List<LikeData>>()
    val likeList: LiveData<List<LikeData>>
        get() = _likeList

    init {
        // viewModel을 실행할 때 getLikeList를 제일 먼저 가져와 주세요
        // init 함수는 매개변수가 없고 반환되는 값이 없는 특별한 함수이다.
        // 생성자를 통해 인스턴스를 만들어 질 때 생성되는 함수이다.
        // 생성자를 초기화할 수 있다.
        getLikeList()
    }

    private fun getLikeList() {
        // 코틀린은 자동적으로 타입을 추론해 주기 때문에 굳이 타입을 안 써줘도 된다.
        val LikeDatas = listOf(
            LikeData(
                background = R.drawable.img_like_background,
                date = "12.4.12-10",
                title = "청소년 영화관 할인"
            ),
            LikeData(
                background = R.drawable.img_like_background,
                date = "12.11-12.17",
                title = "청소년 미술관 할인"
            ),
            LikeData(
                background = R.drawable.img_like_background,
                date = "12.18-12.24",
                title = "청소년 공연장 할인"
            ),
            LikeData(
                background = R.drawable.img_like_background,
                date = "12.25-12.31",
                title = "청소년 박물관 할인"
            ),
            LikeData(
                background = R.drawable.img_like_background,
                date = "1.1-1.7",
                title = "청소년 서점 할인"
            ),
            LikeData(
                background = R.drawable.img_like_background,
                date = "1.8-1.14",
                title = "청소년 강의 할인"
            )
        )
        _likeList.value = LikeDatas
    }
}
