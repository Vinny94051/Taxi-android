package com.example.taximuslim.baseUI.viewmodel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

abstract class NetworkViewModel: BaseViewModel(){
    private val job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    fun fetchData(){
        uiScope.launch {
            try{

            }catch (e: Exception){

            }
        }
    }
}