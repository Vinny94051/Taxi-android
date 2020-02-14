package com.example.taximuslim.presentation.view.driver.settings.changeNumber.validateCode

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taximuslim.App
import com.example.taximuslim.domain.interactors.DriverInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ValidateChangeNumbCodeViewModel : ViewModel() {
    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var interactor: DriverInteractor

    val numb1 = MutableLiveData<String>("")
    val numb2 = MutableLiveData<String>("")
    val numb3 = MutableLiveData<String>("")
    val numb4 = MutableLiveData<String>("")

    val navigate = MutableLiveData<Boolean>(false)


    fun onMainButtonClick(){
        viewModelScope.launch(Dispatchers.Main) {
            try{
                val code = numb1.value!! + numb2.value!! + numb3.value!! + numb3.value!!
                if (code.length == 4){
                    val response = interactor.sendSmsCode(code)
                    if (response){
                        navigate.value = true
                    }else{

                    }
                }else{

                }
            }catch (e: Exception){

            }
        }
    }
}
