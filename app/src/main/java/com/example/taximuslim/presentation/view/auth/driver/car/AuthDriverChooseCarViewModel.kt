package com.example.taximuslim.presentation.view.auth.driver.car

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taximuslim.App
import com.example.taximuslim.data.network.remote.response.driver.CarMark
import com.example.taximuslim.data.network.remote.response.driver.CarModel
import com.example.taximuslim.domain.auth.driver.CarInfoInteractor
import com.example.taximuslim.domain.models.driver.auth.CarColor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthDriverChooseCarViewModel : ViewModel() {

    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var interactor: CarInfoInteractor

    var selectedColor: CarColor? = null
    var selectedMark: CarMark? = null
    private val _selectedModel = MutableLiveData<CarModel>()
    val selectedModel: LiveData<CarModel>
        get() = _selectedModel


    private val _navigateToNext = MutableLiveData<Boolean>()
    val navigateToNext: LiveData<Boolean>
        get() = _navigateToNext

    fun onNavigate() {
        _navigateToNext.value = false
    }

    private val _carColors = MutableLiveData<List<CarColor>>()
    val carColors: LiveData<List<CarColor>>
        get() = _carColors

    private val _carMarks = MutableLiveData<List<CarMark>>()
    val carMarks: LiveData<List<CarMark>>
        get() = _carMarks

    private val _carModels = MutableLiveData<List<CarModel>>()
    val carModels: LiveData<List<CarModel>>
        get() = _carModels

    fun onSelectCarMark(markId: Int) {
        viewModelScope.launch {
            val carModels = interactor.fetchCarModels(markId)
            _carModels.value = carModels
        }
    }

    private val _incorrectData = MutableLiveData<Boolean>(false)
    val incorrectData: LiveData<Boolean>
        get() = _incorrectData

    fun onMainButtonClick() {
        if ((selectedColor == null) || (selectedMark == null) || (selectedModel.value == null)){
            _incorrectData.value = true
        }else{
            _navigateToNext.value = true
        }

    }

    fun onErrorMessage() {
        _incorrectData.value = false
    }

    fun setDefaultValues() {
        viewModelScope.launch(Dispatchers.Main) {
            if (_carColors.value == null) {
                _carColors.value = interactor.fetchCarColors()
                _carMarks.value = interactor.fetchCarMarks()
                _carModels.value = interactor.fetchCarModels(carMarks.value!![0].id)

                selectedColor = carColors.value?.get(0)
                selectedMark = carMarks.value?.get(0)
                _selectedModel.value = carModels.value?.get(0)
            } else {
                //TODO для сохранения состояния
            }
        }
    }

    fun setCarColor(position: Int) {
        val colorList = carColors.value
        colorList?.let { list ->
            selectedColor = list[position]
        }
    }

    fun setCarMark(position: Int) {
        val markList = carMarks.value
        markList?.let { list ->
            selectedMark = list[position]
            viewModelScope.launch(Dispatchers.Main) {
                selectedMark?.let { mark ->
                    _carModels.value = interactor.fetchCarModels(mark.id)
                    _selectedModel.value = carModels.value?.get(0)
                }
            }
        }
    }

    fun setCarModel(position: Int) {
        val modelList = carModels.value
        modelList?.let { list ->
            _selectedModel.value = list[position]
        }
    }

}
