package com.example.taximuslim.presentation.view.auth.driver.carPhoto

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taximuslim.App
import com.example.taximuslim.data.network.remote.request.driver.UploadDriverImageRequest
import com.example.taximuslim.domain.auth.driver.DriverAuthInteractor
import com.example.taximuslim.domain.auth.driver.DriverAuthInteractorImpl
import com.example.taximuslim.presentation.view.auth.driver.LoadingImageStatus
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


    val carImageStatus = MutableLiveData<LoadingImageStatus>(LoadingImageStatus.NULL)
    val certificateImageStatus = MutableLiveData<LoadingImageStatus>(LoadingImageStatus.NULL)

    val carImage = MutableLiveData<Uri>()
    val certificateImage = MutableLiveData<Uri>()

    val takeCarPhoto = MutableLiveData<Boolean>(false)
    val takeCertificatePhoto = MutableLiveData<Boolean>(false)


    fun uploadCarImage() {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                carImageStatus.value = LoadingImageStatus.LOADING
                val filePath = carImage.value?.path ?: ""
                val base64 = filePath.toBase64()
                val request = UploadDriverImageRequest(
                    "car", "registration_certificate", base64
                )
                interactor.uploadDriverImage(request)
                carImageStatus.value = LoadingImageStatus.COMPLETE
            } catch (e: Exception) {
                carImageStatus.value = LoadingImageStatus.ERROR
            }
        }
    }

    fun uploadCertificateImage() {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                certificateImageStatus.value = LoadingImageStatus.LOADING
                val filePath = certificateImage.value?.path ?: ""
                val base64 = filePath.toBase64()
                val request = UploadDriverImageRequest(
                    "car", "car_photo", base64
                )
                interactor.uploadDriverImage(request)
                certificateImageStatus.value = LoadingImageStatus.COMPLETE
            } catch (e: Exception) {
                certificateImageStatus.value = LoadingImageStatus.ERROR
            }
        }
    }

    fun onDeleteCarImageClick() {
        carImage.value = null
        carImageStatus.value = LoadingImageStatus.NULL
    }

    fun onDeleteCertificateClick() {
        certificateImage.value = null
        certificateImageStatus.value = LoadingImageStatus.NULL
    }

    fun onTakeCarPhotoClick() {
        if ((carImageStatus.value == LoadingImageStatus.ERROR)
            || (carImageStatus.value == LoadingImageStatus.NULL)
        ) {
            takeCarPhoto.value = true
        }
    }

    fun onTakeCertificateClick() {
        if ((certificateImageStatus.value == LoadingImageStatus.ERROR)
            || (certificateImageStatus.value == LoadingImageStatus.NULL)
        ) {
            takeCertificatePhoto.value = true
        }
    }


    private val _navigateToNext = MutableLiveData<Boolean>()
    val navigateToNext: LiveData<Boolean>
        get() = _navigateToNext

    fun onNavigate() {
        _navigateToNext.value = false
    }

    fun onMainButtonClick() {
        //TODO(CHECKDATA)
        _navigateToNext.value = true
    }
}
