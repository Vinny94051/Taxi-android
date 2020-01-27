package com.example.taximuslim.presentation.view.driver.driverIncome

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.StringBuilder

class DriverIncomeViewModel : ViewModel() {
    val incomePerMonth = MutableLiveData<StringBuilder>()
    val incomePerWeek = MutableLiveData<StringBuilder>()
    val incomePerDay = MutableLiveData<StringBuilder>()
    val incomePerTrip = MutableLiveData<StringBuilder>()
}
