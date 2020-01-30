package com.example.taximuslim.data.network.api

import com.example.taximuslim.data.network.remote.request.driver.*
import com.example.taximuslim.data.network.remote.response.driver.*
import retrofit2.http.*

interface DriverApi {

    @GET("car")
    suspend fun fetchCarMarks(
        @Header("Authorization") token: String
    ): List<CarMarkResponse>

    @GET("car-model/view")
    suspend fun fetchCarModels(
        @Header("Authorization") token: String,
        @Query("id_car") modelId: Int
    ): List<CarModelsResponse>

    @GET("car-color")
    suspend fun fetchCarColors(
        @Header("Authorization") token: String
    ): List<CarColorsResponse>

    @POST("driver/update-car")
    suspend fun postMarkModelColor(
        @Header("Authorization") token: String,
        @Body body: MarkModelColorRequest
    ): StatusResponse

    @GET("driver/view-car")
    suspend fun fetchInfoABoutCar(
        @Header("Authorization") token: String
    ): CarInfoResponse

    @POST("driver/update-image")
    suspend fun uploadDriverImage(
        @Header("Authorization") token: String,
        @Body body: UploadDriverImageRequest
    ): StatusResponse

    @POST("driver/delete-image")
    suspend fun deleteDriverImage(
        @Header("Authorization") token: String,
        @Body body: DeleteDriverImageRequest
    ): StatusResponse

    @POST("driver/update-profile")
    suspend fun updateProfile(
        @Header("Authorization") token: String,
        @Body body: UpdateProfileRequest
    ): StatusResponse

    @GET("driver/view-profile")
    suspend fun fetchDriverProfile(
        @Header("Authorization") token: String
    ): DriverProfileResponse

    @POST("driver/update-document")
    suspend fun postLicenceNumb(
        @Header("Authorization") token: String,
        @Body body: PostLicenceNumbRequest
    ): StatusResponse

    @GET("driver/view-document")
    suspend fun fetchLicenceNumb(
        @Header("Authorization") token: String
    ): LicenceNumbResponse

    @GET("rule-driver")
    suspend fun fetchDriverRulesList(
        @Header("Authorization") token: String
    ): List<DriverRuleResponse>

    @GET("trip")
    suspend fun fetchOrderHistoryList(
        @Header("Authorization") token: String
    ): List<OrderHistoryResponse>

//    @GET("earnings")
//    suspend fun fetchDriverIncome(
//        @Header("Authorization") token: String
//    )

}