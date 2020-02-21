package com.example.taximuslim.data.repository.driver

import android.content.Context
import android.util.Log
import com.example.taximuslim.App
import com.example.taximuslim.data.network.api.DriverApi
import com.example.taximuslim.data.network.dto.Token
import com.example.taximuslim.data.network.dto.driver.DriverLocation
import com.example.taximuslim.data.network.dto.driver.FetchDriverStatusRequest
import com.example.taximuslim.data.network.dto.driver.PaymentResult
import com.example.taximuslim.data.network.dto.order.OrderRequest
import com.example.taximuslim.data.network.dto.order.OrderStatusResponse
import com.example.taximuslim.data.network.remote.request.driver.ChangeNameRequest
import com.example.taximuslim.data.network.remote.request.driver.ChangePhoneRequest
import com.example.taximuslim.data.network.remote.request.driver.OrderListRequest
import com.example.taximuslim.data.network.remote.request.driver.SmsCodeRequest
import com.example.taximuslim.data.network.remote.response.driver.DriverIncome
import com.example.taximuslim.data.repository.order.MapperOrderStatus
import com.example.taximuslim.domain.models.driver.OrderHistoryModel
import com.example.taximuslim.domain.models.driver.OrderToDriverModel
import com.example.taximuslim.domain.models.driver.ProfileModel
import com.example.taximuslim.domain.models.driver.order.DriverOrderModel
import com.example.taximuslim.domain.order.models.StatusAndDrivers
import com.example.taximuslim.utils.prefference.getAuthHeader
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class DriverRepository {
    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var api: DriverApi

    @Inject
    lateinit var context: Context

    @Inject
    lateinit var token: Token

    suspend fun fetchDriverHistory(): List<OrderHistoryModel> {
        val token = getAuthHeader(context)
        return api.fetchOrderHistoryList(token).map {
            OrderHistoryModel(
                it.auto, it.comment, it.date, it.distance, it.endAddress, it.startLat, it.startLgn,
                it.endLat, it.endLng, it.id, it.clientName, it.driverName, it.clientPhone,
                it.driverPhone, it.positionDriverLat, it.positionDriverLng, it.price,
                it.clientReply, it.request, it.startAddress, it.status, it.time, it.timeToGet
            )
        }
    }

    suspend fun fetchDriverIncome(): DriverIncome {
        val token = getAuthHeader(context)
        return api.fetchDriverIncome(token)
    }

    suspend fun fetchProfile(): ProfileModel {
        val token = getAuthHeader(context)
        val response = api.fetchProfile(token)
        return ProfileModel(
            response.car,
            response.carColor,
            response.carImagePhoto,
            response.carImageRegistrationCertificate,
            response.carModel,
            response.carNumber,
            response.clientIdTrip,
            response.clientName,
            response.clientTripStatus,
            response.documentImageBack,
            response.documentImageFont,
            response.documentNumber,
            response.driver,
            response.driverIdTrip,
            response.driverName,
            response.driverTripStatus,
            response.email,
            response.image,
            response.imageLicenseBack,
            response.imageLicenseFont,
            response.moderationClient,
            response.moderationDriver,
            response.phone,
            response.profile,
            response.surname
        )
    }

    suspend fun fetchBalance(): Double {
        val token = getAuthHeader(context)
        return api.fetchBalance(token)
    }

    suspend fun changeName(name: String): String {
        val token = getAuthHeader(context)
        return (api.changeName(token, ChangeNameRequest(name))).name
    }

    suspend fun changePhone(phone: String): String {
        val token = getAuthHeader(context)
        return (api.changePhone(token, ChangePhoneRequest(phone))).status
    }

    suspend fun sendSmsCode(code: String): Boolean {
        val token = getAuthHeader(context)
        return (api.sendSmsCode(token, SmsCodeRequest(code))).status != "no_code"
    }

    suspend fun sentPaymentResult(paymentToken: String, price: Double) {
        api.sentPaymentResult(getAuthHeader(context), PaymentResult(paymentToken, price.toString()))
    }


    fun fetchTripList(driverLocation: DriverLocation): Observable<List<OrderToDriverModel>> =
        api.fetchTripList(getAuthHeader(context), driverLocation)
            .doOnError {
                Log.e("fetchTripList", it.message + " " + it.localizedMessage)
            }
            .map {
                MapperOrderRequest().mapFromEntity(it)
            }

    fun fetchDriverStatus(body: FetchDriverStatusRequest) : Single<StatusAndDrivers> =
        api.fetchDriverStatus(token.token, body)
            .map { orderStatusResponse ->
                MapperOrderStatus().mapFromEntity(orderStatusResponse)
            }


}