package com.example.taximuslim.presentation.view.driver.profile

import android.content.Context
import androidx.lifecycle.*
import com.example.taximuslim.App
import com.example.taximuslim.data.network.dto.yandex.cashbox.PaymentResponse
import com.example.taximuslim.domain.interactors.DriverInteractor
import com.example.taximuslim.domain.models.driver.ProfileModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class DriverProfileViewModel : ViewModel(), LifecycleObserver {
    init {
        App.appComponent.inject(this)
    }

    val profile = MutableLiveData<ProfileModel>()
    val balance = MutableLiveData<String>("0")
    val payment = MutableLiveData<Boolean>(false)

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
