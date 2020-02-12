package com.example.taximuslim.presentation.view.mainscreen

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.lifecycle.Observer
import com.example.taximuslim.App
import com.example.taximuslim.R
import com.example.taximuslim.baseUI.fragment.BaseFragment
import com.example.taximuslim.baseUI.fragment.BaseFragmentCompanion
import com.example.taximuslim.domain.order.models.StatusAndDrivers
import com.example.taximuslim.presentation.viewmodel.maps.TripViewModel
import com.example.taximuslim.utils.mapfunc.MapManager
import com.example.taximuslim.utils.mapfunc.PolyManager
import com.example.taximuslim.utils.view.MarkerAnimation
import com.example.taximuslim.utils.view.ViewManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import kotlinx.android.synthetic.main.fragment_trip_process.*
import javax.inject.Inject


class TripProcessFragment : BaseFragment(), OnMapReadyCallback {

    init {
        App.appComponent.inject(this)
    }

    companion object : BaseFragmentCompanion() {
        override val ID: String
            get() = "TRIP_PROCESS_FRAGMENT"

        override fun newInstance() =
            TripProcessFragment()
    }

    private lateinit var mMap: GoogleMap
    private lateinit var mapView: MapView
    private lateinit var owner: MapsActivity
    private val viewModel = TripViewModel()
    private lateinit var viewManager: ViewManager
    private lateinit var polyManager: PolyManager
    private var isRouteDrawed = false
    private lateinit var driverMarker: Marker

    @Inject
    lateinit var mapManager: MapManager


    override fun onAttach(context: Context) {
        super.onAttach(context)
        owner = context as MapsActivity
        viewManager = ViewManager(owner)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapView = googleMapTrip
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
        cancelOrderTextView.setOnClickListener {
            MarkerAnimation.hideMarker()
            viewModel.cancelOrder(owner.tripId)
        }
    }

    override fun layoutId(): Int = R.layout.fragment_trip_process

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.isCompassEnabled = false
        showUserLocation()
        mMap.uiSettings.isScrollGesturesEnabled = false
        mMap.uiSettings.isZoomControlsEnabled = false
        polyManager = PolyManager(mMap)
        MarkerAnimation.startAnimation(rootView)
    }


    override fun onResume() {
        super.onResume()
        mapView.onResume()
        initObservers()
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

    private fun initObservers() {
        viewModel.statusLiveData.observe(this, Observer { response ->
            Log.e("RESPONSE::", response.toString())
            when (response.status) {
                1 -> firstStatusAction(response)
                2 -> thirdStatusAction(response)
                3 -> thirdStatusAction(response)
                4 -> fourthStatusAction(response)
                5 -> fiveStatusAction(response)
                6 -> statusSixAction(response)
            }
        })

        viewModel.cancelOrderStatusLiveData.observe(this, Observer { status ->
            when (status.status) {
                true -> owner.removeFragment(this)
                else -> showToast("Что-то пошло не так.")
            }
        })

        viewModel.directionsLiveData.observe(this, Observer { route ->
            if (!isRouteDrawed) {
                polyManager.drawRouteWithOutPadding(route)
                isRouteDrawed = true
            }
        })

        viewModel.fetchOrderStatus(owner.tripId)
    }

    private fun firstStatusAction(response: StatusAndDrivers) {
        if (!router.isFragmentInStack(ChooseDriverFragment.ID)) {
            viewManager.hideViews(userMarker)
            MarkerAnimation.hideMarker()
            ChooseDriverFragment.newInstance().apply {
                drivers = response.drivers
                tripId = owner.tripId

                router.addFragment(
                    this,
                    R.id.fragment_container_trip,
                    ChooseDriverFragment.ID
                )
            }
        }
    }

    private var lastDriverTime: String = ""

    private fun thirdStatusAction(response: StatusAndDrivers) {
        if (!router.isFragmentInStack(DriverOnTheWayFragment.ID) || lastDriverTime != response.timeToGet) {
            val fragment =
                DriverOnTheWayFragment.newInstance()
            fragment.statusAndDrivers = response
            router.replaceFragment(
                fragment,
                R.id.fragment_container_trip,
                DriverOnTheWayFragment.ID
            )
            drawDriverRoute(
                response.driverPositionLatitude,
                response.driverPositionLongitude,
                response.startPointAddress
            )

            viewManager.showViews(cardView, cancelOrderTextView)
            viewManager.hideViews(userMarker)
            lastDriverTime = response.timeToGet
        }
    }

    private fun fourthStatusAction(response: StatusAndDrivers) {
        if (!router.isFragmentInStack(DriverWaitFragment.ID)) {
            val fragment = DriverWaitFragment.newInstance()
            fragment.statusAndDrivers = response

            PolyManager.line?.remove()
            MapManager.markerPointALocation?.remove()
            MapManager.markerPointBLocation?.remove()

            mMap.animateCamera(
                CameraUpdateFactory.newLatLng(
                    LatLng(
                        response.startPointLatitude,
                        response.startPointLongitude
                    )
                )
            )

            driverMarker = mapManager.addMarker(
                mMap,
                LatLng(response.startPointLatitude, response.startPointLongitude),
                R.drawable.ic_driver_car_marker
            )


            router.replaceFragment(
                fragment,
                R.id.fragment_container_trip,
                DriverWaitFragment.ID
            )
        }
        viewManager.showViews(cardView)
        viewManager.hideViews(userMarker, cancelOrderTextView)
    }

    @SuppressLint("SetTextI18n")
    private fun fiveStatusAction(response: StatusAndDrivers) = viewManager.apply {
        isRouteDrawed = false
        driverMarker.remove()
        if (!isRouteDrawed)
            viewModel.loadRoutes(response.startPointAddress, response.endPointAddress)

        hideViews(cardView, cancelOrderTextView)
        timeInTripTextView.text = "Осталось ехать " + response.time
        showViews(timeInTripCardView)
    }

    private fun statusSixAction(response: StatusAndDrivers) {
        if (owner.supportFragmentManager.findFragmentByTag(TripEndFragment.ID) == null) {


            TripEndFragment.newInstance().apply {
                statusAndDrivers = response
                this@TripProcessFragment.owner.replaceFragment(
                    this,
                    R.id.container,
                    TripEndFragment.ID
                )
            }
        }
    }

    private fun drawDriverRoute(
        startLat: Double,
        startLng: Double,
        endPointAddress: String
    ) = viewModel.loadRoutes(
        mapManager.latLngToAddress(LatLng(startLat, startLng)),
        endPointAddress
    )


    private fun addAnimation(view: View) {
        SpringAnimation(view, DynamicAnimation.TRANSLATION_Y, 0f)
    }


}