package com.example.taximuslim.domain.models.driver.auth

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CarMark(
    val id: Int,
    val name: String
): Parcelable