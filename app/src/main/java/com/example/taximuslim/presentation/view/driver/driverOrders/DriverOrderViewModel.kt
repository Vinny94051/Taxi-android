package com.example.taximuslim.presentation.view.driver.driverOrders

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.taximuslim.App
import com.example.taximuslim.baseUI.viewmodel.BaseViewModel
import com.example.taximuslim.data.network.dto.driver.DriverLocation
import com.example.taximuslim.domain.interactors.DriverInteractor
import com.example.taximuslim.domain.models.driver.OrderToDriverModel
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class DriverOrderViewModel : BaseViewModel() {

    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var interactor: DriverInteractor

    val orderToDriverModelListLiveData = MutableLiveData<List<OrderToDriverModel>>()

    fun fetchTripList(driverLocation: DriverLocation) {
        addDisposable(
            interactor.fetchTripList(driverLocation)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response: List<OrderToDriverModel> ->
                    orderToDriverModelListLiveData.value = response
                }, { error ->
                    Log.e(
                        "viewModelExc",
                        error.message + "\n" + error.stackTrace + "\n" + error.localizedMessage + "\n" + error.printStackTrace()
                    )

                })

        )
    }

}
