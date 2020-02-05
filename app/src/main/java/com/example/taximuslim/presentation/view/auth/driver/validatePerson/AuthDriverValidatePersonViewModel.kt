package com.example.taximuslim.presentation.view.auth.driver.validatePerson

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
import com.example.taximuslim.presentation.view.auth.driver.isComplete
import com.example.taximuslim.utils.image.toBase64
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthDriverValidatePersonViewModel : ViewModel() {

    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var interactor: DriverAuthInteractor

    fun initViewModel(driverModel: DriverMainModel){
        if (driverModel.profileImage != null){
            profileImage.value = driverModel.profileImage
            profileImageStatus.value = LoadingStatus.COMPLETE
        }
    }


    val profileImage = MutableLiveData<Uri>()
    val profileImageStatus = MutableLiveData<LoadingStatus>(LoadingStatus.NULL)
    val takePhoto = MutableLiveData<Boolean>(false)

    fun onTakePhotoClick(){
        if ((profileImageStatus.value == LoadingStatus.ERROR)
            || (profileImageStatus.value == LoadingStatus.NULL)
        ) {
            takePhoto.value = true
        }
    }

    fun uploadProfilePhoto(){
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

    fun onDeletePhoto(){
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

    val error = MutableLiveData<Boolean>(false)



    private val _navigate = MutableLiveData<Boolean>()
    val navigate: LiveData<Boolean>
        get() = _navigate

    fun onMainButtonClick(){
        if (profileImageStatus.value.isComplete()){
            _navigate.value = true
        }else{
            error.value = true
        }

    }

    fun onNavigate(){
        _navigate.value = false
    }
}
