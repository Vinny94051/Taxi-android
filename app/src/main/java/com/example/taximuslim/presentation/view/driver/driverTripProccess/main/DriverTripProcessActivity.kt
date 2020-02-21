package com.example.taximuslim.presentation.view.driver.driverTripProccess.main

import android.os.Bundle
import com.example.taximuslim.App
import com.example.taximuslim.R
import com.example.taximuslim.baseUI.activivty.BaseActivity
import com.example.taximuslim.utils.mapfunc.MapManager
import com.example.taximuslim.utils.mapfunc.PolyManager
import com.example.taximuslim.utils.view.MarkerAnimation
import com.example.taximuslim.utils.view.ViewManager
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import kotlinx.android.synthetic.main.fragment_trip_process.*
import javax.inject.Inject

class DriverTripProcessActivity : BaseActivity(), OnMapReadyCallback {


    init {
        App.appComponent.inject(this)
    }

    private lateinit var mMap: GoogleMap
    private lateinit var mapView: MapView
    private lateinit var viewManager: ViewManager
    private lateinit var polyManager: PolyManager
    private var viewModel = DriverTripProcessViewModel()

    @Inject
    lateinit var mapManager: MapManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun layoutId() = R.layout.activity_trip_process_driver

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.isCompassEnabled = false
        mMap.uiSettings.isScrollGesturesEnabled = false
        mMap.uiSettings.isZoomControlsEnabled = false
        polyManager = PolyManager(mMap)
        MarkerAnimation.startAnimation(rootView,R.drawable.driver_circle_vector)
    }


}