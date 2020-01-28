package com.example.taximuslim.data.repository

interface IMapper<T1, T2> {

    fun mapFromEntity(data: T1): T2

    //fun mapToEntity(data: T2): T1
}