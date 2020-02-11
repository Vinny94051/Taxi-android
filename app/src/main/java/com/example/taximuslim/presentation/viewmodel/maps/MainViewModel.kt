package com.example.taximuslim.presentation.viewmodel.maps

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.taximuslim.App
import com.example.taximuslim.baseUI.viewmodel.BaseViewModel
import com.example.taximuslim.data.network.dto.order.TariffRequest
import com.example.taximuslim.domain.models.google.Route
import com.example.taximuslim.domain.models.guide.GuideCategoryModel
import com.example.taximuslim.domain.order.IOrderInteractor
import com.example.taximuslim.domain.order.models.OrderModel
import com.example.taximuslim.domain.order.models.TariffModel
import com.example.taximuslim.utils.location.IUserLocationProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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

    fun setLocation(location: Location) {
        _currentLocation.value = location
    }

    fun loadLocation() =
        userLocationProvider.getLocation { location ->
            _currentLocation.value = location
        }

    val pointBLiveData = MutableLiveData<String>()

    private val _tariffsLiveData = MutableLiveData<TariffModel>()
    val tarriffsLiveData: LiveData<TariffModel>
        get() = _tariffsLiveData

    fun loadTariffs(location: TariffRequest) {
        interactor.getTariffies(location) { tariffs ->
            _tariffsLiveData.value = tariffs
        }
    }

    private val _directionsLiveData = MutableLiveData<Route>()
    val directionsLiveData: LiveData<Route>
        get() = _directionsLiveData

    fun loadRoutes(start: String, end: String) {
        interactor.getDirections(start, end) { route ->
            _directionsLiveData.value = route
        }
    }

    private val _guideCategoriesLiveData = MutableLiveData<List<GuideCategoryModel>>()
    val guideCategoriesLiveData: LiveData<List<GuideCategoryModel>>
        get() = _guideCategoriesLiveData

    fun loadGuideCategories() {
        viewModelScope.launch(Dispatchers.Main) {
            interactor.getCategories { categoryList ->
                _guideCategoriesLiveData.value = categoryList

            }
        }
    }

    private val _tripIdLiveData = MutableLiveData<Int>()
    val tripIdLivedata: LiveData<Int>
        get() = _tripIdLiveData

    fun createOrder(order: OrderModel) {
        viewModelScope.launch {
            interactor.createOrder(order) { tripId ->
                _tripIdLiveData.value = tripId
            }
        }
    }


}