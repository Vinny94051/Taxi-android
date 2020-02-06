package com.example.taximuslim.presentation.view.auth.driver.documents

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taximuslim.App
import com.example.taximuslim.data.network.remote.request.driver.DeleteDriverImageRequest
import com.example.taximuslim.data.network.remote.request.driver.UpdateDriverLicenceRequest
import com.example.taximuslim.data.network.remote.request.driver.UploadDriverImageRequest
import com.example.taximuslim.domain.auth.driver.DriverAuthInteractor
import com.example.taximuslim.domain.models.driver.auth.DriverMainModel
import com.example.taximuslim.presentation.view.auth.driver.LoadingStatus
import com.example.taximuslim.presentation.view.auth.driver.isComplete
import com.example.taximuslim.utils.image.toBase64
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthDriverDocumentsViewModel : ViewModel() {

    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var interactor: DriverAuthInteractor


    val driverLicenceFrontStatus = MutableLiveData<LoadingStatus>(LoadingStatus.NULL)
    val driverLicenceBackStatus = MutableLiveData<LoadingStatus>(LoadingStatus.NULL)

    val driverLicenceFront = MutableLiveData<Uri>()
    val driverLicenceBack = MutableLiveData<Uri>()

    val takeLicenceFront = MutableLiveData<Boolean>(false)
    val takeLicenceBack = MutableLiveData<Boolean>(false)

    val driverLicenceNumb = MutableLiveData<String>("")
    val driverLicenceNumbStatus = MutableLiveData<LoadingStatus>(LoadingStatus.NULL)

    init {
        driverLicenceNumb.observeForever {
            if (it.isNotBlank()) {
                driverLicenceNumbStatus.value = LoadingStatus.COMPLETE
            }
        }
    }

    fun initVIewModel(driverModel: DriverMainModel) {
        driverLicenceNumb.value = driverModel.driverLicenceNumb
        driverModel.driverLicenceFront?.let {
            driverLicenceFront.value = it
            driverLicenceFrontStatus.value = LoadingStatus.COMPLETE
        }
        driverModel.driverLicenceBack?.let {
            driverLicenceBack.value = it
            driverLicenceBackStatus.value = LoadingStatus.COMPLETE
        }
    }

    fun onDriverLicenceBackClick() {
        if (driverLicenceBackStatus.value == LoadingStatus.NULL) {
            takeLicenceBack.value = true
        }
        if (driverLicenceBackStatus.value == LoadingStatus.ERROR) {
            uploadDriverLicenceBack()
        }
    }

    fun onDriverLicenceFrontClick() {
        if (driverLicenceFrontStatus.value == LoadingStatus.NULL) {
            takeLicenceFront.value = true
        }
        if (driverLicenceFrontStatus.value == LoadingStatus.ERROR) {
            uploadDriverLicenceFront()
        }
    }

    fun uploadDriverLicenceFront() {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                driverLicenceFrontStatus.value = LoadingStatus.LOADING
                val filePath = driverLicenceFront.value?.path ?: ""
                val base64 = filePath.toBase64()
                val request = UploadDriverImageRequest(
                    "document", "font", base64
                )
                val response = interactor.uploadDriverImage(request)
                driverLicenceFrontStatus.value = if (response) LoadingStatus.COMPLETE
                else LoadingStatus.ERROR
            } catch (e: Exception) {
                driverLicenceFrontStatus.value = LoadingStatus.ERROR
            }
        }
    }

    fun uploadDriverLicenceBack() {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                driverLicenceBackStatus.value = LoadingStatus.LOADING
                val filePath = driverLicenceFront.value?.path ?: ""
                val base64 = filePath.toBase64()
                val request = UploadDriverImageRequest(
                    "document", "back", base64
                )
                val response = interactor.uploadDriverImage(request)
                driverLicenceBackStatus.value = if (response) LoadingStatus.COMPLETE
                else LoadingStatus.ERROR
            } catch (e: Exception) {
                driverLicenceBackStatus.value = LoadingStatus.ERROR
            }
        }
    }

    fun deleteDriverLicenceFront() {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val request = DeleteDriverImageRequest(
                    "document", "font"
                )
                interactor.deleteDriverImage(request)
                driverLicenceFront.value = null
                driverLicenceFrontStatus.value = LoadingStatus.NULL
            } catch (e: Exception) {
            }
        }
    }

    fun deleteDriverLicenceBack() {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val request = DeleteDriverImageRequest(
                    "document", "back"
                )
                interactor.deleteDriverImage(request)
                driverLicenceBack.value = null
                driverLicenceBackStatus.value = LoadingStatus.NULL
            } catch (e: Exception) {
            }
        }
    }

    private val _navigate = MutableLiveData<Boolean>()
    val navigate: LiveData<Boolean>
        get() = _navigate

    fun onNavigate() {
        _navigate.value = false
    }

    val error = MutableLiveData<Boolean>(false)

    fun onMainButtonClick() {
        if (driverLicenceFrontStatus.value.isComplete() &&
            (driverLicenceBackStatus.value.isComplete()) &&
            (driverLicenceNumbStatus.value.isComplete())
        ) {
            updateDocuments()
        } else {
            error.value = true
            if (driverLicenceNumb.value?.isBlank() == true) {
                driverLicenceNumbStatus.value = LoadingStatus.ERROR
            }
        }
    }

    private fun updateDocuments() {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val request = UpdateDriverLicenceRequest(driverLicenceNumb.value!!)
                _navigate.value = interactor.updateDriverLicence(request)
            } catch (e: Exception) {

            }
        }
    }
}
