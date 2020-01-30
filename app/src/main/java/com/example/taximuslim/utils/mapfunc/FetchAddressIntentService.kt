package com.example.taximuslim.utils.mapfunc

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.util.Log
import com.example.taximuslim.data.network.dto.order.TariffRequest
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.io.IOException
import java.util.*

class FetchAddressIntentService(private val context: Context) {

    companion object {
        private const val UNKNOWN_ADDRESS = "Cannot define your address."
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

    fun addMarkerAndMoveCameraToIt(
        mMap: GoogleMap,
        location: LatLng,
        zoom: Float,
        markerIconId: Int
    ) {
        mMap.addMarker(
            MarkerOptions()
                .position(location)
                .icon(BitmapDescriptorFactory.fromResource(markerIconId))
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location))
        mMap.animateCamera(CameraUpdateFactory.zoomTo(zoom))
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

}