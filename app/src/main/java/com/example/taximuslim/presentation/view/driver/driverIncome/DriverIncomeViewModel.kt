package com.example.taximuslim.presentation.view.driver.driverIncome

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DriverIncomeViewModel : ViewModel() {
    val incomePerMonth = MutableLiveData<String>()
    val incomePerWeek = MutableLiveData<String>()
    val incomePerDay = MutableLiveData<String>()
    val incomePerTrip = MutableLiveData<String>()
}
