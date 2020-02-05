package com.example.taximuslim.data.repository.guide

import com.example.taximuslim.data.network.dto.guide.GuideCategoryResponse
import com.example.taximuslim.data.repository.IMapper
import com.example.taximuslim.domain.models.guide.GuideCategoryModel

class CategoryResponseMapper : IMapper<List<GuideCategoryResponse>, List<GuideCategoryModel>> {
    override fun mapFromEntity(data: List<GuideCategoryResponse>): List<GuideCategoryModel> =
        data.map { responseItem ->
            GuideCategoryModel(
                responseItem.id,
                responseItem.name,
                responseItem.imageUrl
            )
        }
}