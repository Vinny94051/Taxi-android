package com.example.taximuslim.presentation.viewmodel.maps

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.taximuslim.App
import com.example.taximuslim.baseUI.viewmodel.BaseViewModel
import com.example.taximuslim.domain.models.google.Route
import com.example.taximuslim.domain.order.IOrderInteractor
import com.example.taximuslim.domain.order.models.BooleanStatus
import com.example.taximuslim.domain.order.models.StatusAndDrivers
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class TripViewModel : BaseViewModel() {

    init {
        App.appComponent.inject(this)
    }

    companion object {
        const val TAG = "TripViewModel: "
    }

    @Inject
    lateinit var interactor: IOrderInteractor


    private val _statusLiveData = MutableLiveData<StatusAndDrivers>()
    val statusLiveData: LiveData<StatusAndDrivers>
        get() = _statusLiveData


    fun fetchOrderStatus(tripId: Int) {
        addDisposable(
            interactor.fetchOrderStatus(tripId)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError { t ->
                    Log.e(TAG, t.message.toString())
                }
                .subscribe { response ->
                    _statusLiveData.value = response
                }
        )
    }


    private val _cancelOrderStatusLiveData = MutableLiveData<BooleanStatus>()
    val cancelOrderStatusLiveData: LiveData<BooleanStatus>
        get() = _cancelOrderStatusLiveData

    fun cancelOrder(tripId: Int) {
        addDisposable(
            interactor.cancelOrder(tripId)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError { t ->
                    Log.e(TAG, t.message.toString())
                }
                .subscribe { response ->
                    _cancelOrderStatusLiveData.value = response
                }
        )
    }

    private val _chooseDriverLiveData = MutableLiveData<BooleanStatus>()
    val chooseDriverLiveData: LiveData<BooleanStatus>
        get() = _chooseDriverLiveData


    fun chooseDriver(tripId: Int, driverId: Int) {
        addDisposable(
            interactor.chooseDriver(tripId, driverId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response ->
                    _chooseDriverLiveData.value = response
                }
        )
    }

    private val _directionsLiveData = MutableLiveData<Route>()
    val directionsLiveData: LiveData<Route>
        get() = _directionsLiveData

    fun loadRoutes(start: String, end: String) {
        interactor.getDirections(start, end) { route ->
            _directionsLiveData.value = route
        }
    }
}