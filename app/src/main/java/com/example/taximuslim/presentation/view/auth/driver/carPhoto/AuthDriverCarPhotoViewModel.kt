package com.example.taximuslim.presentation.view.auth.driver.carPhoto

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taximuslim.App
import com.example.taximuslim.data.network.remote.request.driver.DeleteDriverImageRequest
import com.example.taximuslim.data.network.remote.request.driver.UploadDriverImageRequest
import com.example.taximuslim.domain.auth.driver.DriverAuthInteractor
import com.example.taximuslim.domain.models.driver.auth.DriverMainModel
import com.example.taximuslim.presentation.view.auth.driver.LoadingStatus
import com.example.taximuslim.utils.image.toBase64
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthDriverCarPhotoViewModel : ViewModel() {


    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var interactor: DriverAuthInteractor


    val carImageStatus = MutableLiveData<LoadingStatus>(LoadingStatus.NULL)
    val certificateImageStatus = MutableLiveData<LoadingStatus>(LoadingStatus.NULL)

    val carImage = MutableLiveData<Uri>()
    val certificateImage = MutableLiveData<Uri>()

    val takeCarPhoto = MutableLiveData<Boolean>(false)
    val takeCertificatePhoto = MutableLiveData<Boolean>(false)

    fun initViewModel(driverModel: DriverMainModel) {
        driverModel.carImage?.let {
            carImage.value = it
            carImageStatus.value = LoadingStatus.COMPLETE
        }
        driverModel.carCertificateImage?.let {
            certificateImage.value = it
            certificateImageStatus.value = LoadingStatus.COMPLETE
        }
    }

    fun uploadCarImage() {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                carImageStatus.value = LoadingStatus.LOADING
                val filePath = carImage.value?.path ?: ""
                val base64 = filePath.toBase64()
                val request = UploadDriverImageRequest(
                    "car", "car_photo", base64
                )
                val response = interactor.uploadDriverImage(request)
                carImageStatus.value = if (response) LoadingStatus.COMPLETE
                else LoadingStatus.ERROR
            } catch (e: Exception) {
                carImageStatus.value = LoadingStatus.ERROR
            }
        }
    }

    fun uploadCertificateImage() {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                certificateImageStatus.value = LoadingStatus.LOADING
                val filePath = certificateImage.value?.path ?: ""
                val base64 = filePath.toBase64()
                val request = UploadDriverImageRequest(
                    "car", "registration_certificate", base64
                )
                val response = interactor.uploadDriverImage(request)
                certificateImageStatus.value = if (response) LoadingStatus.COMPLETE
                else LoadingStatus.ERROR
            } catch (e: Exception) {
                certificateImageStatus.value = LoadingStatus.ERROR
            }
        }
    }

    fun onDeleteCarImageClick() {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val request = DeleteDriverImageRequest(
                    "car", "car_photo"
                )
                interactor.deleteDriverImage(request)
                carImage.value = null
                carImageStatus.value = LoadingStatus.NULL
            } catch (e: Exception) {

            }
        }

    }

    fun onDeleteCertificateClick() {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val request = DeleteDriverImageRequest(
                    "car", "registration_certificate"
                )
                interactor.deleteDriverImage(request)
                certificateImage.value = null
                certificateImageStatus.value = LoadingStatus.NULL
            } catch (e: Exception) {

            }
        }
    }

    fun onTakeCarPhotoClick() {
        if (carImageStatus.value == LoadingStatus.NULL) {
            takeCarPhoto.value = true
        }
        if (carImageStatus.value == LoadingStatus.ERROR) {
            uploadCarImage()
        }
    }

    fun onTakeCertificateClick() {
        if (certificateImageStatus.value == LoadingStatus.NULL) {
            takeCertificatePhoto.value = true
        }
        if (certificateImageStatus.value == LoadingStatus.ERROR) {
            uploadCertificateImage()
        }
    }


    private val _navigateToNext = MutableLiveData<Boolean>(false)
    val navigateToNext: LiveData<Boolean>
        get() = _navigateToNext

    fun onNavigate() {
        _navigateToNext.value = false
    }

    val showToast = MutableLiveData<Boolean>(false)

    fun onMainButtonClick() {
        if ((carImageStatus.value == LoadingStatus.COMPLETE)
            && (certificateImageStatus.value == LoadingStatus.COMPLETE)
        ) {
            _navigateToNext.value = true
        } else {
            showToast.value = true
        }

    }
}
