package com.example.taximuslim.presenter.maps

import android.location.Location
import androidx.lifecycle.MutableLiveData
import com.example.taximuslim.utils.location.MyLocationProvider

class MainPresenter {
    var currentLocation = MutableLiveData<Location>()
  //  var priceLiveData = MutableLiveData<Editable>()

    fun updateLocation() {
        MyLocationProvider.getLocation { location ->
            currentLocation.value = location
        }
    }

//    fun updatePrice(){
//        PriceDialogWindow.getPrice { price ->
//            priceLiveData.value = price
//        }
//    }
}