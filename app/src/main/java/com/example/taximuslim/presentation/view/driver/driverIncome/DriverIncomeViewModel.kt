package com.example.taximuslim.presentation.view.driver.driverIncome

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DriverIncomeViewModel : ViewModel() {
    val incomePerMonth = MutableLiveData<String>("0")
    val incomePerWeek = MutableLiveData<String>("0")
    val incomePerDay = MutableLiveData<String>("0")
    val incomePerTrip = MutableLiveData<String>("0")
}
