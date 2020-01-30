package com.example.taximuslim.presentation.viewmodel.maps

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.taximuslim.App
import com.example.taximuslim.baseUI.viewmodel.BaseViewModel
import com.example.taximuslim.data.network.dto.order.TariffRequest
import com.example.taximuslim.data.recycle.places.PlacesModelForRecycleViewHorizontal
import com.example.taximuslim.domain.order.IOrderInteractor
import com.example.taximuslim.domain.order.models.TariffModel
import com.example.taximuslim.presentation.view.clientorder.list.PlacesModel
import com.example.taximuslim.utils.location.IUserLocationProvider
import javax.inject.Inject

class MainViewModel : BaseViewModel() {

    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var userLocationProvider: IUserLocationProvider

    @Inject
    lateinit var interactor: IOrderInteractor


    private var _currentLocation = MutableLiveData<Location>()
    val currentLocation: LiveData<Location>
        get() = _currentLocation

    fun setLocation(location : Location){
        _currentLocation.value = location
    }

    fun loadLocation() =
        userLocationProvider.getLocation { location ->
            _currentLocation.value = location
        }

    val pointBLiveData = MutableLiveData<String>()


    var placesForMapsView = MutableLiveData<ArrayList<PlacesModel>>()
    var placesModel = PlacesModelForRecycleViewHorizontal(0, "")

    fun loadPlaces() {
        placesModel.load { places ->
            val placesForMapsViewLocal = arrayListOf<PlacesModel>()
            for (place in places) {
                placesForMapsViewLocal.add(PlacesModel(place.img, place.text))
            }
            placesForMapsView.value = placesForMapsViewLocal
        }
    }

    private val _tariffsLiveData = MutableLiveData<TariffModel>()
    val tarriffsLiveData: LiveData<TariffModel>
        get() = _tariffsLiveData

    fun loadTariffs(token: String, location: TariffRequest) {
        interactor.getTariffies(token, location) { tariffs ->
            _tariffsLiveData.value = tariffs
        }
    }


}