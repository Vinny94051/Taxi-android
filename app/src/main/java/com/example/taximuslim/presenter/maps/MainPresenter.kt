package com.example.taximuslim.presenter.maps

import android.location.Location
import androidx.lifecycle.MutableLiveData
import com.example.taximuslim.baseUI.presenter.BasePresenter
import com.example.taximuslim.data.recycle.places.PlacesModelForRecycleViewHorizontal
import com.example.taximuslim.presentation.view.main.list.PlacesModel
import com.example.taximuslim.utils.location.MyLocationProvider

class MainPresenter : BasePresenter() {
    var currentLocation = MutableLiveData<Location>()
    var placesForMapsView = MutableLiveData<ArrayList<PlacesModel>>()

    var placesModel = PlacesModelForRecycleViewHorizontal(0,"")

    fun loadLocation() {
        MyLocationProvider.getLocation { location ->
            currentLocation.value = location
        }
    }

    fun loadPlaces(){
        placesModel.load { places ->
            val placesForMapsViewLocal = arrayListOf<PlacesModel>()
            for(place in places){
                placesForMapsViewLocal.add(PlacesModel(place.img,place.text))
            }
            placesForMapsView.value = placesForMapsViewLocal
        }

    }


}