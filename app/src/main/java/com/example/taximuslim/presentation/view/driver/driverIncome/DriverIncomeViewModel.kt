package com.example.taximuslim.presentation.view.driver.driverIncome

import androidx.lifecycle.*
import com.example.taximuslim.App
import com.example.taximuslim.domain.interactors.DriverInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DriverIncomeViewModel : ViewModel(), LifecycleObserver {

    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var interactor: DriverInteractor

    val incomePerMonth = MutableLiveData<String>("0")
    val incomePerWeek = MutableLiveData<String>("0")
    val incomePerDay = MutableLiveData<String>("0")
    val incomePerTrip = MutableLiveData<String>("0")

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun fetchDriverIncome() {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val income = interactor.fetchDriverIncome()
                incomePerTrip.value = income.earningsTrip
                incomePerDay.value = income.earningsDay
                incomePerWeek.value = income.earningsWeek
                incomePerMonth.value = income.earningsMonth
            } catch (e: Exception) {
            }
        }
    }
}
