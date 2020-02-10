package com.example.taximuslim.presentation.view.mainscreen

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.taximuslim.App
import com.example.taximuslim.R
import com.example.taximuslim.baseUI.fragment.BaseFragment
import com.example.taximuslim.baseUI.fragment.BaseFragmentCompanion
import com.example.taximuslim.presentation.viewmodel.maps.TripViewModel
import com.example.taximuslim.utils.mapfunc.MapManager
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import kotlinx.android.synthetic.main.fragment_trip_process.*
import javax.inject.Inject


class TripProcessFragment : BaseFragment(), OnMapReadyCallback {

    init {
        App.appComponent.inject(this)
    }

    companion object : BaseFragmentCompanion() {
        override val ID: String
            get() = "TRIP_PROCESS_FRAGMENT"

        override fun newInstance(): Fragment =
            TripProcessFragment()
    }

    private lateinit var mMap: GoogleMap
    private lateinit var mapView: MapView
    private lateinit var owner: MapsActivity
    private val viewModel = TripViewModel()

    @Inject
    lateinit var mapManager: MapManager


    override fun onAttach(context: Context) {
        super.onAttach(context)
        owner = context as MapsActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapView = googleMapTrip
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
    }

    override fun layoutId(): Int = R.layout.fragment_trip_process

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.isCompassEnabled = false
        showUserLocation()
        mMap.uiSettings.isScrollGesturesEnabled = false
        mMap.uiSettings.isZoomControlsEnabled = false
        //TODO  addAnimation(userMarker)
    }


    override fun onResume() {
        super.onResume()
        mapView.onResume()

        viewModel.statusLiveData.observe(this, Observer { response ->
            Log.e("RESPONSE::::::", response.toString())
        })

        viewModel.fetchOrderStatus(owner.tripId)
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
        viewModel.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }


    private fun showUserLocation() {
        val userLocation = mapManager.getLocationFromAddress(owner.userLocation)
        mapManager.addMarker(
            mMap,
            userLocation,
            resources.getDrawable(R.drawable.marker_empty)
        )
        mapManager.moveCameraToLocation(mMap, userLocation, MapsActivity.MAP_ZOOM)
    }


    private fun addAnimation(view: View) {
        SpringAnimation(view, DynamicAnimation.TRANSLATION_Y, 0f)
    }
}