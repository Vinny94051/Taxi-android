package com.example.taximuslim.presenter.maps

import android.location.Location
import androidx.lifecycle.MutableLiveData
import com.example.taximuslim.utils.location.MyLocationProvider

class MainPresenter {
    var currentLocation = MutableLiveData<Location>()

    fun updateLocation() {
        MyLocationProvider.getLocation { location ->
            currentLocation.value = location
        }
    }
}