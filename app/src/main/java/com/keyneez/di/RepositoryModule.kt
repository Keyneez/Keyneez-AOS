package com.keyneez.di

import com.keyneez.data.repository.ContentRepository
import com.keyneez.data.repository.ContentRepositoryImpl
import com.keyneez.data.repository.UserRepository
import com.keyneez.data.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun providesContentRepository(
        contentRepositoryImpl: ContentRepositoryImpl
    ): ContentRepository

    @Binds
    @Singleton
    abstract fun providesUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository
}
