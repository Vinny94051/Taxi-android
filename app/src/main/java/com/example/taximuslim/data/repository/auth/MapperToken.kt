package com.example.taximuslim.data.repository.auth

import com.example.taximuslim.data.repository.IMapper

class MapperToken : IMapper<Any, String> {
    override fun mapFromEntity(data: Any): String {
        return data.toString()
    }
}

