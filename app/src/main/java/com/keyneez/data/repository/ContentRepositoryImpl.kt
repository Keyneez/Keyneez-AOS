package com.keyneez.data.repository

import com.keyneez.data.source.ContentDataSource
import javax.inject.Inject

class ContentRepositoryImpl @Inject constructor(
    private val contentDataSource: ContentDataSource
) : ContentRepository{
    override suspend fun getContent() {
        TODO("Not yet implemented")
    }
}
