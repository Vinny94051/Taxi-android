package com.example.taximuslim.presentation.view.driver.driverOrderHistory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taximuslim.App
import com.example.taximuslim.domain.interactors.DriverInteractor
import com.example.taximuslim.domain.models.driver.OrderHistoryModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DriverOrderHistoryViewModel : ViewModel() {
    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var interactor: DriverInteractor

    val ethernetError = MutableLiveData<Boolean>()

    private val _orderHistory = MutableLiveData<List<OrderHistoryModel>>()
    val orderHistory: LiveData<List<OrderHistoryModel>>
        get() = _orderHistory

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
