package com.example.taximuslim.presentation.view.driver.profile

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.lifecycle.*
import com.example.taximuslim.App
import com.example.taximuslim.data.network.dto.yandex.cashbox.PaymentRequest
import com.example.taximuslim.data.network.dto.yandex.cashbox.PaymentResponse
import com.example.taximuslim.domain.interactors.DriverInteractor
import com.example.taximuslim.domain.models.driver.ProfileModel
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.yandex.money.android.sdk.Amount
import ru.yandex.money.android.sdk.Checkout
import ru.yandex.money.android.sdk.PaymentParameters
import ru.yandex.money.android.sdk.SavePaymentMethod
import java.lang.Exception
import java.math.BigDecimal
import java.util.*
import javax.inject.Inject

class DriverProfileViewModel : ViewModel(), LifecycleObserver {
    init {
        App.appComponent.inject(this)
    }

    val profile = MutableLiveData<ProfileModel>()
    val balance = MutableLiveData<String>("0")
    val payment = MutableLiveData<PaymentResponse>()

    @Inject
    lateinit var interactor: DriverInteractor

    @Inject
    lateinit var context: Context

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun fetchProfile() {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val profileResponse = interactor.fetchProfile()
                profile.value = profileResponse

                val balanceResponse = interactor.fetchBalance()
                balance.value = balanceResponse.toString()
            } catch (e: Exception) {
            }
        }
    }

    fun onChangeProfileClick() {

    }


    fun onPayClick() {
//        interactor.makePayment(payment)
//            .observeOn(AndroidSchedulers.mainThread())
//            .doOnError { exception ->
//                Log.e("DriverProfileVM:", exception.message.toString())
//            }
//            .subscribe { response ->
//                this@DriverProfileViewModel.payment.value = response
//            }
    }
}
