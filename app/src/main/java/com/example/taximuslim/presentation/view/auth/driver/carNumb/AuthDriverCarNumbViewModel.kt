package com.example.taximuslim.presentation.view.auth.driver.carNumb

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taximuslim.App
import com.example.taximuslim.domain.auth.driver.DriverAuthInteractor
import com.example.taximuslim.domain.models.driver.auth.DriverMainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthDriverCarNumbViewModel : ViewModel() {

    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var interactor: DriverAuthInteractor

    private val _navigateToNext = MutableLiveData<Boolean>()
    val navigateToNext: LiveData<Boolean>
        get() = _navigateToNext

    val carNumb = MutableLiveData<String>()

    private val _correct = MutableLiveData<Boolean>(null)
    val correct: LiveData<Boolean>
    get() = _correct

    fun initViewModel(driverModel: DriverMainModel){
        carNumb.value = driverModel.carNumb
    }

    fun onMainButtonClick() {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val numb = carNumb.value ?: ""
                if(numb.isNotEmpty()){
                    val response = interactor.sendCarNumb(carNumb.value ?: "")
                    if (response) {
                        _navigateToNext.value = true
                    } else {
                        _correct.value = false
                    }
                }else{
                    _correct.value = false
                }

            }catch(e: Exception){
                e.printStackTrace()
            }
        }
    }

    fun onNumbChange(){
        _correct.value = true
    }


    fun onNavigate() {
        _navigateToNext.value = false
    }
}
