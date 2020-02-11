package com.example.taximuslim.presentation.view.mainscreen

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
import com.example.taximuslim.presentation.viewmodel.maps.TripViewModel
import com.example.taximuslim.utils.mapfunc.MapManager
import com.example.taximuslim.utils.view.ViewManager
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

        override fun newInstance() =
            TripProcessFragment()
    }

    private lateinit var mMap: GoogleMap
    private lateinit var mapView: MapView
    private lateinit var owner: MapsActivity
    private val viewModel = TripViewModel()
    private lateinit var chooseDriverFragment: ChooseDriverFragment
    private lateinit var viewManager: ViewManager

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
    }


    override fun onResume() {
        super.onResume()
        mapView.onResume()

        viewModel.statusLiveData.observe(this, Observer { response ->
            Log.e("RESPONSE::::::", response.toString())
            when (response.status) {
                1 -> {
                    if (!router.isFragmentInStack(ChooseDriverFragment.ID)) {
                        viewManager.hideViews(userMarker)

                        chooseDriverFragment =
                            ChooseDriverFragment.newInstance()
                        (chooseDriverFragment).apply {
                            drivers = response.drivers
                            tripId = owner.tripId
                        }
                        router.addFragment(
                            chooseDriverFragment,
                            R.id.fragment_container_trip,
                            ChooseDriverFragment.ID
                        )
                    }
                }
                2 -> {
                    viewManager.hideViews(cardView, cancelOrderTextView)
                    viewManager.showViews(userMarker)
                }

                3 -> {
                    if (!router.isFragmentInStack(DriverOnTheWayFragment.ID)) {
                        val fragment =
                            DriverOnTheWayFragment.newInstance()
                        fragment.statusAndDrivers = response
                        router.replaceFragment(
                            fragment,
                            R.id.fragment_container_trip,
                            DriverOnTheWayFragment.ID
                        )
                        viewManager.showViews(cardView, cancelOrderTextView)
                        viewManager.hideViews(userMarker)
                    }
                }
                4 -> {
                    if (!router.isFragmentInStack(DriverWaitFragment.ID)) {
                        val fragment = DriverWaitFragment.newInstance()
                        fragment.statusAndDrivers = response

                        router.replaceFragment(
                            fragment,
                            R.id.fragment_container_trip,
                            DriverWaitFragment.ID
                        )
                    }
                }

                5 -> {
                    viewManager.hideViews(cardView, cancelOrderTextView)
                    viewManager.showViews(timeInTripCardView)
                }

                6 -> {

                    val fragment = TripEndFragment.newInstance()
                    fragment.statusAndDrivers = response
                    owner.replaceFragment(fragment, R.id.container, TripEndFragment.ID)
                }

            }
        })

        viewModel.cancelOrderStatusLiveData.observe(this, Observer { status ->
            when (status.status) {
                true -> owner.removeFragment(this)
                else -> showToast("Что-то пошло не так.")
            }
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