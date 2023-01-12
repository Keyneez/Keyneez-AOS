package com.keyneez.di

import com.keyneez.data.service.ContentService
import com.keyneez.data.service.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    fun providesContentService(retrofit: Retrofit): ContentService =
        retrofit.create(ContentService::class.java)

    @Provides
    fun providesUserService(retrofit: Retrofit): UserService =
        retrofit.create(UserService::class.java)
}
