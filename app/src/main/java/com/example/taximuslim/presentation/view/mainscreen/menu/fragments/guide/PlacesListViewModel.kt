package com.example.taximuslim.presentation.view.mainscreen.menu.fragments.guide

import androidx.lifecycle.*
import com.example.taximuslim.App
import com.example.taximuslim.baseUI.viewmodel.BaseViewModel
import com.example.taximuslim.domain.models.guide.PlaceByLocationModel
import com.example.taximuslim.domain.models.guide.UserPlaceByLocationModel
import com.example.taximuslim.domain.order.IOrderInteractor
import kotlinx.coroutines.launch
import javax.inject.Inject

class PlacesListViewModel(private val userPlaceByLocation: UserPlaceByLocationModel) : BaseViewModel(), LifecycleObserver {

    init {
        App.appComponent.inject(this)
    }


    @Inject
    lateinit var interactor: IOrderInteractor


    private val _placesLiveData = MutableLiveData<List<PlaceByLocationModel>>()
    val placesLiveData: LiveData<List<PlaceByLocationModel>>
        get() = _placesLiveData

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun loadPlacesByCategory() {
        viewModelScope.launch {
            interactor.getPlaceByLocation(userPlaceByLocation) { places ->
                _placesLiveData.value = places
            }
        }
    }

}

class PlacesListViewModelFactory(private val userPlaceByLocation: UserPlaceByLocationModel) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PlacesListViewModel::class.java))
            return PlacesListViewModel(userPlaceByLocation) as T
        else
            throw Exception()
    }
}