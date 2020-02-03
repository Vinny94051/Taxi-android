package com.example.taximuslim.domain.models.driver.auth

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CarModel(
    val id: Int,
    val name: String,
    val tariff: String
): Parcelable