package com.example.taximuslim.domain.interactors

import com.example.taximuslim.App
import com.example.taximuslim.data.network.dto.driver.DriverLocation
import com.example.taximuslim.data.network.dto.driver.FetchDriverStatusRequest
import com.example.taximuslim.data.network.dto.yandex.cashbox.PaymentRequest
import com.example.taximuslim.data.network.dto.yandex.cashbox.PaymentResponse
import com.example.taximuslim.data.network.dto.yandex.cashbox.SentIdPayRequest
import com.example.taximuslim.data.network.remote.response.driver.DriverIncome
import com.example.taximuslim.data.repository.driver.DriverRepository
import com.example.taximuslim.data.repository.yandex.IYandexRepository
import com.example.taximuslim.domain.models.driver.ProfileModel
import com.example.taximuslim.domain.models.driver.OrderHistoryModel
import com.example.taximuslim.domain.models.driver.OrderToDriverModel
import com.example.taximuslim.domain.order.models.StatusAndDrivers
import com.example.taximuslim.domain.yandex.IYandexInteractor
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class DriverInteractorImpl : DriverInteractor, IYandexInteractor {


    init {
        App.appComponent.inject(this)
    }

    private val repository =
        DriverRepository()

    @Inject
    lateinit var yandexRepo: IYandexRepository


    override suspend fun fetchOrderHistory(): List<OrderHistoryModel> {
        return repository.fetchDriverHistory()
    }

    override suspend fun fetchDriverIncome(): DriverIncome {
        return repository.fetchDriverIncome()
    }

    override suspend fun fetchProfile(): ProfileModel {
        return repository.fetchProfile()
    }

    override suspend fun fetchBalance(): Double {
        return repository.fetchBalance()
    }

    override suspend fun changePhone(phone: String): String {
        return repository.changePhone(phone)
    }

    override suspend fun changeName(name: String): String {
        return repository.changeName(name)
    }

    override suspend fun sendSmsCode(code: String): Boolean {
        return repository.sendSmsCode(code)
    }

    override fun makePayment(payment: PaymentRequest): Single<PaymentResponse> =
        yandexRepo.makePayment(payment)
            .subscribeOn(Schedulers.io())

    override fun sentPayId(payId: SentIdPayRequest): Single<PaymentResponse> =
        yandexRepo.sentPayId(payId)
            .subscribeOn(Schedulers.io())

    override fun fetchTripList(driverLocation: DriverLocation): Observable<List<OrderToDriverModel>> =
        repository.fetchTripList(driverLocation)
            .subscribeOn(Schedulers.io())
            .delay(3, TimeUnit.SECONDS)
            .repeat()

    override fun fetchDriverStatus(body: FetchDriverStatusRequest): Observable<StatusAndDrivers> =
        repository.fetchDriverStatus(body)
            .toObservable()
            .subscribeOn(Schedulers.io())
            .delay(3, TimeUnit.SECONDS)
            .repeat()


}
