package com.example.taximuslim.presentation.view.mainscreen.menu.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.taximuslim.domain.models.guide.PlaceByLocationModel

class SharedViewModel : ViewModel() {

    val placeLiveData = MutableLiveData<PlaceByLocationModel>()

    fun placeItem(place : PlaceByLocationModel){
        placeLiveData.value = place
    }

}