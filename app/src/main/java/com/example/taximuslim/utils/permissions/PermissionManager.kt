package com.example.taximuslim.utils.permissions

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class PermissionManager(private val activity: Activity) {

    private fun getLocationPermissionStatus() = ContextCompat.checkSelfPermission(
        activity,
        Manifest.permission.ACCESS_COARSE_LOCATION
    ) != PackageManager.PERMISSION_GRANTED

    private fun getLocationPermissionStatus2() = ContextCompat.checkSelfPermission(
        activity,
        Manifest.permission.ACCESS_FINE_LOCATION
    ) != PackageManager.PERMISSION_GRANTED

    fun checkLocationPermissions() =
        if (getLocationPermissionStatus() || getLocationPermissionStatus2()) {
            ActivityCompat.requestPermissions(activity, arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ), PermissionConstants.LOCATION_PERMISSION_REQUEST_CODE)
            false
        } else
            true
}