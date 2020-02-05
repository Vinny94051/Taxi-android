package com.example.taximuslim.presentation.view.auth.driver.aboutYou

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taximuslim.App
import com.example.taximuslim.data.network.remote.request.driver.DeleteDriverImageRequest
import com.example.taximuslim.data.network.remote.request.driver.UploadDriverImageRequest
import com.example.taximuslim.domain.auth.driver.DriverAuthInteractor
import com.example.taximuslim.presentation.view.auth.driver.LoadingStatus
import com.example.taximuslim.presentation.view.auth.driver.isComplete
import com.example.taximuslim.utils.image.toBase64
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthDriverAboutYouViewModel : ViewModel() {

    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var interactor: DriverAuthInteractor

    val profileImageStatus = MutableLiveData<LoadingStatus>(LoadingStatus.NULL)
    val taxiLicenceFrontStatus = MutableLiveData<LoadingStatus>(LoadingStatus.NULL)
    val taxiLicenceBackStatus = MutableLiveData<LoadingStatus>(LoadingStatus.NULL)

    val profileImage = MutableLiveData<Uri>()
    val taxiLicenceFront = MutableLiveData<Uri>()
    val taxiLicenceBack = MutableLiveData<Uri>()

    val takeProfileImage = MutableLiveData<Boolean>(false)
    val takeLicenceFrontImage = MutableLiveData<Boolean>(false)
    val takeLicenceBackImage = MutableLiveData<Boolean>(false)

    val profileName = MutableLiveData<String>()
    val profileSurname = MutableLiveData<String>()
    val profileEmail = MutableLiveData<String>()

    val nameStatus = MutableLiveData<LoadingStatus>(LoadingStatus.NULL)
    val surnameStatus = MutableLiveData<LoadingStatus>(LoadingStatus.NULL)
    val emailStatus = MutableLiveData<LoadingStatus>(LoadingStatus.NULL)

    init {
        profileName.observeForever {
            if (it.isNotBlank()) {
                nameStatus.value = LoadingStatus.COMPLETE
            }
        }
        profileSurname.observeForever {
            if (it.isNotBlank()) {
                surnameStatus.value = LoadingStatus.COMPLETE
            }
        }
        profileEmail.observeForever {
            if (it.isNotBlank()) {
                emailStatus.value = LoadingStatus.COMPLETE
            }
        }
    }

    fun onTakeProfileImage() {
        if ((profileImageStatus.value == LoadingStatus.ERROR)
            || (profileImageStatus.value == LoadingStatus.NULL)
        ) {
            takeProfileImage.value = true
        }
    }

    fun onTakeLicenceFrontImage() {
        if ((taxiLicenceFrontStatus.value == LoadingStatus.ERROR)
            || (taxiLicenceFrontStatus.value == LoadingStatus.NULL)
        ) {
            takeLicenceFrontImage.value = true
        }
    }

    fun onTakeLicenceBackImage() {
        if ((taxiLicenceBackStatus.value == LoadingStatus.ERROR)
            || (taxiLicenceBackStatus.value == LoadingStatus.NULL)
        ) {
            takeLicenceBackImage.value = true
        }
    }

    fun uploadProfileImage() {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                profileImageStatus.value = LoadingStatus.LOADING
                val filePath = profileImage.value?.path ?: ""
                val base64 = filePath.toBase64()
                val request = UploadDriverImageRequest(
                    "profile", "icon", base64
                )
                interactor.uploadDriverImage(request)
                profileImageStatus.value = LoadingStatus.COMPLETE
            } catch (e: Exception) {
                profileImageStatus.value = LoadingStatus.ERROR
            }
        }
    }

    fun uploadLicenceFront() {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                taxiLicenceFrontStatus.value = LoadingStatus.LOADING
                val filePath = taxiLicenceFront.value?.path ?: ""
                val base64 = filePath.toBase64()
                val request = UploadDriverImageRequest(
                    "profile", "license_font", base64
                )
                interactor.uploadDriverImage(request)
                taxiLicenceFrontStatus.value = LoadingStatus.COMPLETE
            } catch (e: Exception) {
                taxiLicenceFrontStatus.value = LoadingStatus.ERROR
            }
        }
    }

    fun uploadLicenceBack() {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                taxiLicenceBackStatus.value = LoadingStatus.LOADING
                val filePath = taxiLicenceBack.value?.path ?: ""
                val base64 = filePath.toBase64()
                val request = UploadDriverImageRequest(
                    "profile", "license_back", base64
                )
                interactor.uploadDriverImage(request)
                taxiLicenceBackStatus.value = LoadingStatus.COMPLETE
            } catch (e: Exception) {
                taxiLicenceBackStatus.value = LoadingStatus.ERROR
            }
        }
    }

    fun deleteProfileImage() {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val request = DeleteDriverImageRequest(
                    "profile", "icon"
                )
                interactor.deleteDriverImage(request)
                profileImage.value = null
                profileImageStatus.value = LoadingStatus.NULL
            } catch (e: Exception) {

            }
        }
    }

    fun deleteLicenceFront() {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val request = DeleteDriverImageRequest(
                    "profile", "license_font"
                )
                interactor.deleteDriverImage(request)
                taxiLicenceFront.value = null
                taxiLicenceFrontStatus.value = LoadingStatus.NULL
            } catch (e: Exception) {

            }
        }
    }

    fun deleteLicenceBack() {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val request = DeleteDriverImageRequest(
                    "profile", "license_back"
                )
                interactor.deleteDriverImage(request)
                taxiLicenceBack.value = null
                taxiLicenceBackStatus.value = LoadingStatus.NULL
            } catch (e: Exception) {

            }
        }
    }


    private val _navigate = MutableLiveData<Boolean>()
    val navigate: LiveData<Boolean>
        get() = _navigate

    val error = MutableLiveData<Boolean>()

    fun onMainButtonClick() {
        if ((nameStatus.value.isComplete()) && (surnameStatus.value.isComplete()) &&
            (emailStatus.value.isComplete()) && (profileImageStatus.value.isComplete()) &&
            (taxiLicenceFrontStatus.value.isComplete()) && (taxiLicenceBackStatus.value.isComplete())
        ) {
            _navigate.value = true
        }else{
            error.value = true
            if (profileName.value?.isBlank() == true){
                nameStatus.value = LoadingStatus.ERROR
            }
            if (profileSurname.value?.isBlank() == true){
                surnameStatus.value = LoadingStatus.ERROR
            }
            if (profileEmail.value?.isBlank() == true){
                emailStatus.value = LoadingStatus.ERROR
            }
        }

    }

    fun onNavigate() {
        _navigate.value = false
    }
}
