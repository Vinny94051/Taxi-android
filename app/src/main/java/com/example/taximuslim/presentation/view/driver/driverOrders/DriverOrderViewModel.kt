package com.example.taximuslim.presentation.view.driver.driverOrders

import androidx.lifecycle.*
import com.example.taximuslim.App
import com.example.taximuslim.data.network.remote.request.driver.OrderListRequest
import com.example.taximuslim.domain.interactors.DriverInteractor
import com.example.taximuslim.domain.models.driver.order.DriverOrderModel
import com.example.taximuslim.utils.location.IUserLocationProvider
import com.example.taximuslim.utils.toLatLng
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DriverOrderViewModel : ViewModel(), LifecycleObserver {

    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var interactor: DriverInteractor

    @Inject
    lateinit var location: IUserLocationProvider

    val driverOrderList = MutableLiveData<List<DriverOrderModel>>()

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun fetchDriverOrder() {
        try {
            location.getLocation { currentLocation ->
                currentLocation?.let { location ->
                    val latLng = location.toLatLng()
                    val lat = latLng.latitude.toString()
                    val lng = latLng.longitude.toString()
                    viewModelScope.launch(Dispatchers.Main) {
                        try {
                            val orderList = interactor.fetchOrderList(
                                OrderListRequest(lat, lng)
                            )
                            driverOrderList.value = orderList
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
