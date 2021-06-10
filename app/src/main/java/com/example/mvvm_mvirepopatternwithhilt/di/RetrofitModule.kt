package com.example.mvvm_mvirepopatternwithhilt.di

import com.example.mvvm_mvirepopatternwithhilt.model.Blog
import com.example.mvvm_mvirepopatternwithhilt.retrofit.BlogNetworkEntity
import com.example.mvvm_mvirepopatternwithhilt.retrofit.BlogRetrofit
import com.example.mvvm_mvirepopatternwithhilt.retrofit.NetworkMapper
import com.example.mvvm_mvirepopatternwithhilt.util.EntityMapper
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RetrofitModule {



    @Provides
    @Singleton
    fun providesGsonBuilder(): Gson{
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()// provide Expose annotation in BlogNetworkEntity
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson):Retrofit.Builder{
        return Retrofit.Builder()
            .baseUrl(BlogRetrofit.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))

    }

    @Provides
    @Singleton
    fun provideBlogService(retrofit: Retrofit.Builder): BlogRetrofit{
        return retrofit
            .build()
            .create(BlogRetrofit::class.java)
    }

}