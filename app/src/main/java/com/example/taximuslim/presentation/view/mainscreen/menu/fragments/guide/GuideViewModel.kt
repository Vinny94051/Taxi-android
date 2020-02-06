package com.example.taximuslim.presentation.view.mainscreen.menu.fragments.guide

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taximuslim.App
import com.example.taximuslim.domain.models.guide.GuideCategoryModel
import com.example.taximuslim.domain.order.IOrderInteractor
import com.example.taximuslim.utils.location.IUserLocationProvider
import kotlinx.coroutines.launch
import javax.inject.Inject

class GuideViewModel : ViewModel() {

    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var interactor: IOrderInteractor

    @Inject
    lateinit var userLocationProvider: IUserLocationProvider


    private val _categoriesLiveData = MutableLiveData<List<GuideCategoryModel>>()
    val categoriesLiveData: LiveData<List<GuideCategoryModel>>
        get() = _categoriesLiveData

    fun loadGuideCategories() {
        viewModelScope.launch {
            interactor.getCategories { categories ->
                _categoriesLiveData.value = categories
            }
        }
    }


    private var _currentLocation = MutableLiveData<Location>()
    val currentLocation: LiveData<Location>
        get() = _currentLocation

    fun loadLocation() =
        userLocationProvider.getLocation { location ->
            _currentLocation.value = location
        }
}