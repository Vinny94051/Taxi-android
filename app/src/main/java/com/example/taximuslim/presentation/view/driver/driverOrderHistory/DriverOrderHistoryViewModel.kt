package com.example.taximuslim.presentation.view.driver.driverOrderHistory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taximuslim.App
import com.example.taximuslim.domain.interactors.DriverInteractor
import com.example.taximuslim.domain.models.driver.OrderHistoryModel
import com.example.taximuslim.domain.order.IOrderInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DriverOrderHistoryViewModel : ViewModel() {

    private val _orderHistory = MutableLiveData<List<OrderHistoryModel>>()
    val orderHistory: LiveData<List<OrderHistoryModel>>
        get() = _orderHistory

    init {
        App.appComponent.inject(this)
        orderHistory.observeForever{
            it.forEach {orderHistoryModel ->
                googleInteractor.getDirections(orderHistoryModel.startAddress, orderHistoryModel.endAddress){
                    orderHistoryModel.route = it
                }
            }
            loadHistory.value = true
        }
    }

    @Inject
    lateinit var interactor: DriverInteractor

    @Inject
    lateinit var googleInteractor: IOrderInteractor

    val loadHistory = MutableLiveData<Boolean>(false)

    val ethernetError = MutableLiveData<Boolean>()



    fun fetchOrderHistory(){
        viewModelScope.launch(Dispatchers.Main) {
            try{
                val list = interactor.fetchOrderHistory()
                _orderHistory.value = list
            }catch (e: Exception){
                ethernetError.value = true
            }
        }
    }
}
