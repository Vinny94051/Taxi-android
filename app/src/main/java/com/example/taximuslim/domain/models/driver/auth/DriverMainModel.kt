package com.example.taximuslim.domain.models.driver.auth

import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DriverMainModel(
    var carColor: CarColor? = null,
    var carMark: CarMark? = null,
    var carModel: CarModel? = null,
    var carNumb: String = "",
    var carImage: Uri? = null,
    var carCertificateImage: Uri? = null,

    var profileImage: Uri? = null,
    var profileName: String = "",
    var profileSurname: String = "",
    var profileEmail: String = "",

    var taxiLicenceFront: Uri? = null,
    var taxiLicenceBack: Uri? = null,

    var driverLicenceNumb: String = "",
    var driverLicenceFront: Uri? = null,
    var driverLicenceBack: Uri? = null

): Parcelable