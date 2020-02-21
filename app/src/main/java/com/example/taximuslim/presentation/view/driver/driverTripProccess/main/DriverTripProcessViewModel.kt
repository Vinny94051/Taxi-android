package com.example.taximuslim.presentation.view.driver.driverTripProccess.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.taximuslim.App
import com.example.taximuslim.baseUI.viewmodel.BaseViewModel
import com.example.taximuslim.data.network.dto.driver.FetchDriverStatusRequest
import com.example.taximuslim.domain.interactors.DriverInteractor
import com.example.taximuslim.domain.order.models.StatusAndDrivers
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class DriverTripProcessViewModel : BaseViewModel() {

    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var driverInteractor: DriverInteractor


    private var _driverStatusLiveData = MutableLiveData<StatusAndDrivers>()
    val driverStatusLiveData: LiveData<StatusAndDrivers>
        get() = _driverStatusLiveData

    fun fetchDriverStatus(body: FetchDriverStatusRequest) {
        addDisposable(
            driverInteractor.fetchDriverStatus(body)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    _driverStatusLiveData.value = response
                }, { exception ->
                    exception.printStackTrace()
                })
        )
    }

}