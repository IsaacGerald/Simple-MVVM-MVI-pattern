package com.example.mvvm_mvirepopatternwithhilt.room

import com.example.mvvm_mvirepopatternwithhilt.model.Blog
import com.example.mvvm_mvirepopatternwithhilt.retrofit.BlogNetworkEntity
import com.example.mvvm_mvirepopatternwithhilt.util.EntityMapper
import org.w3c.dom.Entity
import javax.inject.Inject

class CacheMapper
@Inject constructor():EntityMapper<BlogCacheEntity, Blog> {
    override fun mapFromEntity(entity: BlogCacheEntity): Blog {
        return Blog(
            id = entity.id,
            body = entity.body,
            title = entity.title,
            category = entity.category,
            image = entity.image

        )
    }

    override fun mapToEntity(domainModel: Blog): BlogCacheEntity {
        return BlogCacheEntity(
            id = domainModel.id,
            body = domainModel.body,
            title = domainModel.title,
            category = domainModel.category,
            image = domainModel.image

        )
    }

    fun mapFromEntityList(entities: List<BlogCacheEntity>): List<Blog>{
        return entities.map { mapFromEntity(it)}
    }

    fun mapToEntityList(domains: List<Blog>): List<BlogCacheEntity>{
        return domains.map { mapToEntity(it)}
    }
}