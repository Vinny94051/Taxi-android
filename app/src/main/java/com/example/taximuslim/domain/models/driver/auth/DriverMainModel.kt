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
    var carCertificateImage: Uri? = null
): Parcelable