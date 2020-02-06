package com.example.taximuslim.presentation.viewmodel.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taximuslim.App
import com.example.taximuslim.domain.models.guide.GuideCategoryModel
import com.example.taximuslim.domain.order.IOrderInteractor
import kotlinx.coroutines.launch
import javax.inject.Inject

class GuideViewModel : ViewModel() {

    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var interactor: IOrderInteractor


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
}