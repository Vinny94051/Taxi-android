package com.example.taximuslim.data.network.api

import com.example.taximuslim.data.network.dto.guide.GuideCategoryResponse
import com.example.taximuslim.data.network.dto.guide.PlaceByLocationRequest
import com.example.taximuslim.data.network.dto.guide.PlaceByLocationResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface GuideApi {

    @GET("place-category")
    suspend fun getCategories(
        @Header("Authorization") token : String
    ) : List<GuideCategoryResponse>

    @POST("place/category")
    suspend fun getPlacesByLocation(
        @Header("Authorization") token : String,
        @Body placeByLocationRequest : PlaceByLocationRequest
    ) : List<PlaceByLocationResponse>
}