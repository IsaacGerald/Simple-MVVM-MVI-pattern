package com.example.mvvm_mvirepopatternwithhilt.retrofit

import com.example.mvvm_mvirepopatternwithhilt.model.Blog
import com.example.mvvm_mvirepopatternwithhilt.util.EntityMapper
import javax.inject.Inject

class NetworkMapper
@Inject constructor() : EntityMapper<BlogNetworkEntity, Blog> {
    override fun mapFromEntity(entity: BlogNetworkEntity): Blog {
        return Blog(
            id = entity.id,
            body = entity.body,
            category = entity.category,
            image = entity.image,
            title = entity.title

        )
    }

    override fun mapToEntity(domainModel: Blog): BlogNetworkEntity {
        return BlogNetworkEntity(
            id = domainModel.id,
            body = domainModel.body,
            category = domainModel.category,
            image = domainModel.image,
            title = domainModel.title

        )
    }

    fun mapFromEntityList(entities: List<BlogNetworkEntity>): List<Blog>{
        return entities.map { mapFromEntity(it)}
    }

    fun mapToEntityList(domains: List<Blog>): List<BlogNetworkEntity>{
        return domains.map { mapToEntity(it)}
    }

}