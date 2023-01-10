package com.keyneez.presentation.main.home.popular

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.keyneez.data.entity.HomeData
import com.lab.keyneez.R
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor() : ViewModel() {
    private val _itemList = MutableLiveData<List<HomeData>>()
    val itemList: LiveData<List<HomeData>>
        get() = _itemList

    init {
        getItemList()
    }

    private fun getItemList() {
        val mainList = listOf(
            HomeData(
                background = R.drawable.img_home_item_background,
                title = "청소년 미술관 할인"
            ),
            HomeData(
                background = R.drawable.img_home_item_background,
                title = "청소년 미술관 할인"
            ),
            HomeData(
                background = R.drawable.img_home_item_background,
                title = "청소년 미술관 할인"
            ),
            HomeData(
                background = R.drawable.img_home_item_background,
                title = "청소년 미술관 할인"
            )
        )
        _itemList.value = mainList
    }
}
