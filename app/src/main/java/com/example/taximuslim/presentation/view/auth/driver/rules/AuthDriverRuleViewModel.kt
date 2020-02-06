package com.example.taximuslim.presentation.view.auth.driver.rules

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taximuslim.App
import com.example.taximuslim.domain.auth.driver.DriverAuthInteractor
import com.example.taximuslim.domain.models.driver.auth.DriverRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthDriverRuleViewModel : ViewModel() {

    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var interactor: DriverAuthInteractor



    val rules = MutableLiveData<List<DriverRule>>(mutableListOf())
    val ruleCaption = MutableLiveData<String>()
    val ruleDescription = MutableLiveData<String>()
    val ruleImage = MutableLiveData<Uri>()
    var rulePosition = 0

    init {
        fetchRuleList()
        rules.observeForever{
            if (it.isNotEmpty()){
                rulePosition = 0
                ruleCaption.value = rules.value!![0].headLine
                ruleDescription.value = rules.value!![0].text
                ruleImage.value = rules.value!![0].image
            }
        }
    }

    private fun fetchRuleList(){
        viewModelScope.launch(Dispatchers.Main) {
            try{
                val list = interactor.fetchDriverRules()
                rules.value = list
            }catch (e: Exception){

            }
        }
    }


    fun nextRule() {
        rulePosition++
        if (rulePosition == rules.value!!.size){
            _navigateToNext.value = true
        }else{
            rulePosition = 0
            ruleCaption.value = rules.value!![rulePosition].headLine
            ruleDescription.value = rules.value!![rulePosition].text
            ruleImage.value = rules.value!![rulePosition].image
        }

    }

    private val _navigateToNext = MutableLiveData<Boolean>()
    val navigateToNext: LiveData<Boolean>
        get() = _navigateToNext

    fun onNavigateToNext(){
        _navigateToNext.value = false
    }

}
