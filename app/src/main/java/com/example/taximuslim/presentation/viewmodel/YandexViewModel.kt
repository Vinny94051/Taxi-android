package com.example.taximuslim.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.taximuslim.App
import com.example.taximuslim.baseUI.viewmodel.BaseViewModel
import com.example.taximuslim.data.network.dto.yandex.cashbox.PaymentRequest
import com.example.taximuslim.data.network.dto.yandex.cashbox.PaymentResponse
import com.example.taximuslim.data.network.dto.yandex.cashbox.SentIdPayRequest
import com.example.taximuslim.domain.yandex.IYandexInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class YandexViewModel : BaseViewModel() {
    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var yandexInteractor: IYandexInteractor

    val paymentLiveData = MutableLiveData<PaymentResponse>()
    val payIdLiveData = MutableLiveData<PaymentResponse>()

    fun makePayment(payment: PaymentRequest) {
        addDisposable(
            yandexInteractor.makePayment(payment)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError { exception ->
                    Log.e("YandexViewModel_1:", exception.message.toString())
                }
                .subscribe { response ->
                    this.paymentLiveData.value = response
                }
        )
    }

    fun sentPayId(payId: SentIdPayRequest) {
        addDisposable(
            yandexInteractor.sentPayId(payId)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError { exception ->
                    Log.e("YandexViewModel_2:", exception.message.toString())
                }
                .subscribe { response ->
                    this.payIdLiveData.value = response
                }
        )
    }


}