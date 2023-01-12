package com.keyneez.presentation.main.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.keyneez.data.entity.SearchData
import com.lab.keyneez.R

class SearchViewModel : ViewModel() {
    private val _searchList = MutableLiveData<List<SearchData>>()
    val searchList: LiveData<List<SearchData>>
        get() = _searchList

    fun getSearchPostData() {
        // 코틀린은 자동적으로 타입을 추론해 주기 때문에 굳이 타입을 안 써줘도 된다.
        val searchDatas = listOf(
            SearchData(
                background = R.drawable.img_like_background,
                date = "12.4-12-10",
                title = "청소년\n영화관 할인",
                liked = true
            ),
            SearchData(
                background = R.drawable.img_like_background,
                date = "12.11-12.17",
                title = "청소년\n미술관 할인",
                liked = false
            ),
            SearchData(
                background = R.drawable.img_like_background,
                date = "12.18-12.24",
                title = "청소년\n공연장 할인",
                liked = false
            ),
            SearchData(
                background = R.drawable.img_like_background,
                date = "12.25-12.31",
                title = "청소년\n박물관 할인",
                liked = true
            ),
            SearchData(
                background = R.drawable.img_like_background,
                date = "1.1-1.7",
                title = "청소년\n서점 할인",
                liked = false
            ),
            SearchData(
                background = R.drawable.img_like_background,
                date = "1.8-1.14",
                title = "청소년\n강의 할인",
                liked = false
            )
        )
        _searchList.value = searchDatas
    }
}
