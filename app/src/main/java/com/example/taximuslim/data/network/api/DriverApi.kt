package com.example.taximuslim.data.network.api

import com.example.taximuslim.data.network.dto.driver.PaymentResult
import com.example.taximuslim.data.network.dto.order.OrderRequest
import com.example.taximuslim.data.network.remote.request.driver.*
import com.example.taximuslim.data.network.remote.response.driver.*
import com.example.taximuslim.domain.models.driver.auth.CarInfo
import com.example.taximuslim.domain.models.driver.auth.DriverProfile
import com.example.taximuslim.domain.models.driver.auth.LicenceNumb
import com.example.taximuslim.domain.models.driver.order.OrderHistoryResponse
import retrofit2.http.*

interface DriverApi {

    @GET("car")
    suspend fun fetchCarMarks(
        @Header("Authorization") token: String
    ): List<CarMarkResponse>

    @GET("car-model/view")
    suspend fun fetchCarModels(
        @Header("Authorization") token: String,
        @Query("id_car") markId: Int
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
    suspend fun fetchInfoAboutCar(
        @Header("Authorization") token: String
    ): CarInfo

    @POST("driver/update-car-number")
    suspend fun sendCarNumb(
        @Header("Authorization") token: String,
        @Body body: DriverCarNumbRequest
    ): StatusResponse

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
    ): DriverProfile

    @POST("driver/update-document")
    suspend fun updateDriverLicence(
        @Header("Authorization") token: String,
        @Body body: UpdateDriverLicenceRequest
    ): StatusResponse

    @GET("driver/view-document")
    suspend fun fetchLicenceNumb(
        @Header("Authorization") token: String
    ): LicenceNumb

    @GET("rule-driver")
    suspend fun fetchDriverRulesList(
        @Header("Authorization") token: String
    ): List<DriverRuleResponse>

    @POST("driver/create")
    suspend fun createDriver(
        @Header("Authorization") token: String
    ): StatusResponse


    @GET("trip")
    suspend fun fetchOrderHistoryList(
        @Header("Authorization") token: String
    ): List<OrderHistoryResponse>

    @GET("earnings")
    suspend fun fetchDriverIncome(
        @Header("Authorization") token: String
    ): DriverIncome

    @GET("profile")
    suspend fun fetchProfile(
        @Header("Authorization") token: String
    ): ProfileResponse

    @GET("money")
    suspend fun fetchBalance(
        @Header("Authorization") token: String
    ): Double


    @POST("profile/update-name-client")
    suspend fun changeName(
        @Header("Authorization") token: String,
        @Body body: ChangeNameRequest
    ): ChangeNameResponse

    @POST("profile/update-phone-client")
    suspend fun changePhone(
        @Header("Authorization") token: String,
        @Body body: ChangePhoneRequest
    ): StatusResponse

    @POST("profile/update-phone-code-client")
    suspend fun sendSmsCode(
        @Header("Authorization") token: String,
        @Body body: SmsCodeRequest
    ): StatusResponse

    @GET("trip/trip-orders")
    suspend fun fetchTripList(
        @Header("Authorization") token: String,
        @Body body: OrderRequest
    )  : List<OrderResponse>

    @POST("money/pay")
    suspend fun sentPaymentResult(
        @Header("Authorization") token: String,
        @Body paymentResult : PaymentResult
    )



}