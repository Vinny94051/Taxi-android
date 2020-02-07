package com.example.taximuslim.domain.models.driver.auth

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CarColor(
    val id: Int = 0,
    val name: String = ""
): Parcelable