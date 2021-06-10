package com.example.mvvm_mvirepopatternwithhilt.repository

import com.example.mvvm_mvirepopatternwithhilt.model.Blog
import com.example.mvvm_mvirepopatternwithhilt.retrofit.BlogRetrofit
import com.example.mvvm_mvirepopatternwithhilt.retrofit.NetworkMapper
import com.example.mvvm_mvirepopatternwithhilt.room.BlogDao
import com.example.mvvm_mvirepopatternwithhilt.room.BlogDatabase
import com.example.mvvm_mvirepopatternwithhilt.room.CacheMapper
import com.example.mvvm_mvirepopatternwithhilt.util.DataState
import com.example.mvvm_mvirepopatternwithhilt.util.EntityMapper
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepository
@Inject constructor(
    private val retrofit: BlogRetrofit,
    private val blogDao: BlogDao,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
) {

    suspend fun getBlog(): Flow<DataState<List<Blog>>> = flow {

        emit(DataState.Loading)
        delay(1000)

        try {
            val networkBlogs = retrofit.get()
            val blogs = networkMapper.mapFromEntityList(networkBlogs)
            for (blog in blogs){
                blogDao.insert(cacheMapper.mapToEntity(blog))
            }
            val cachedBlogs = blogDao.get()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedBlogs)))

        }catch (e: Exception){
            emit(DataState.Error(e))
        }

    }
}