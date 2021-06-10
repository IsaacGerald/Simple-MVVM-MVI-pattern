package com.example.mvvm_mvirepopatternwithhilt.di

import com.example.mvvm_mvirepopatternwithhilt.repository.MainRepository
import com.example.mvvm_mvirepopatternwithhilt.retrofit.BlogRetrofit
import com.example.mvvm_mvirepopatternwithhilt.retrofit.NetworkMapper
import com.example.mvvm_mvirepopatternwithhilt.room.BlogDao
import com.example.mvvm_mvirepopatternwithhilt.room.CacheMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun providesMainRepository(
        blogDao: BlogDao,
        retrofit: BlogRetrofit,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ): MainRepository {
        return MainRepository(retrofit, blogDao, cacheMapper, networkMapper)
    }

}