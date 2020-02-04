package com.example.taximuslim.utils.mapfunc

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.util.Log
import android.view.View
import com.example.taximuslim.data.network.dto.order.TariffRequest
import com.example.taximuslim.utils.isNotEmpty
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.*
import java.io.IOException
import java.util.*

class MapManager(private val context: Context) {

    companion object {
        private const val UNKNOWN_ADDRESS = "Cannot define your address."

        var markerPointALocation: Marker? = null
        var markerPointBLocation: Marker? = null
    }

    fun latLngToAddress(location: LatLng): String {
        var address = UNKNOWN_ADDRESS
        val geocoder = Geocoder(context, Locale.getDefault())
        try {
            val addresses: List<Address> =
                geocoder.getFromLocation(location.latitude, location.longitude, 1)
            address = addresses[0].getAddressLine(0)
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return address
    }

    fun getCountry(location: LatLng): TariffRequest? {
        var countryAndCity: TariffRequest? = null
        val geocoder = Geocoder(context, Locale.getDefault())
        try {
            val locations: List<Address> =
                geocoder.getFromLocation(location.latitude, location.longitude, 1)
            val address: String = locations[0].getAddressLine(0)
            countryAndCity = TariffRequest(locations[0].countryName, getCityFromAddress(address))
        } catch (ex: IOException) {
            ex.printStackTrace()
        }

        return countryAndCity
    }

    private fun getCityFromAddress(address: String): String {
        var city: String = address.removeRange(0, 4)
        var i = 0
        while (city[i] != ',') {
            city = city.removeRange(0, 1)
        }
        city = city.removeRange(0, 2)
        var town = ""
        i = 0
        while (city[i] != ',') {
            town += city[i]
            i++
        }
        return town
    }

    fun addMarker(
        mMap: GoogleMap,
        location: LatLng,
        markerIconId: Int
    ): Marker =
        mMap.addMarker(
            MarkerOptions()
                .position(location)
                .icon(BitmapDescriptorFactory.fromResource(markerIconId))
        )


    fun moveCameraToLocation(mMap: GoogleMap, location: LatLng, zoom: Float) {
        mMap.apply {
            moveCamera(CameraUpdateFactory.newLatLng(location))
            animateCamera(CameraUpdateFactory.zoomTo(zoom))
        }
    }

    fun setPaddings(mMap: GoogleMap, root: View) {
        mMap.setPadding(
            root.width / 18,
            0,
            root.width / 18,
            root.height / 2 * 1.5.toInt()
        )
    }


    fun getLocationFromAddress(strAddress: String): LatLng? {
        val address: MutableList<Address> =
            try {
                Geocoder(context).getFromLocationName(strAddress, 1)
                    ?: return null
            } catch (ex: IOException) {
                Log.e("GetLocationFromAddress:", "IOE")
                ex.printStackTrace()
                return null
            }
        return LatLng(address[0].latitude, address[0].longitude)
    }


    fun moveCameraToTwoMarkers(mMap : GoogleMap) {
        if (markerPointBLocation.isNotEmpty()
            && markerPointALocation.isNotEmpty()
        ) {
            mMap.animateCamera(createCameraUpdateObject())
        }
    }

    private fun createCameraUpdateObject(): CameraUpdate =
        CameraUpdateFactory.newLatLngBounds(
            LatLngBounds
                .builder()
                .include(markerPointALocation?.position)
                .include(markerPointBLocation?.position)
                .build(), 200
        )




}