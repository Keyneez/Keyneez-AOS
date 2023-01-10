package com.keyneez.data.source

import com.keyneez.data.service.ContentService
import javax.inject.Inject

class ContentDataSource @Inject constructor(
    private val contentService: ContentService
)
