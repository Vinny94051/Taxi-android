package com.example.taximuslim.presentation.view.clientorder

import android.app.Activity
import android.content.pm.PackageManager
import android.graphics.Point
import android.location.Location
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taximuslim.baseUI.activivty.BaseActivity
import com.example.taximuslim.presentation.view.design.dialogswindow.CommentAlert
import com.example.taximuslim.presentation.view.design.dialogswindow.PriceAlert
import com.example.taximuslim.utils.mapfunc.FetchAddressIntentService
import com.example.taximuslim.presentation.view.menu.fragments.GuideFragment
import com.example.taximuslim.presentation.view.menu.fragments.HelpFragment
import com.example.taximuslim.presentation.view.clientorder.list.MapsCustomAdapter
import com.example.taximuslim.presentation.view.clientorder.managers.NavigationDrawerManager
import com.example.taximuslim.presentation.view.menu.fragments.HistoryFragment
import com.example.taximuslim.presentation.view.menu.fragments.SettingsFragment
import com.example.taximuslim.presentation.viewmodel.maps.MainViewModel
import com.example.taximuslim.utils.*
import com.example.taximuslim.utils.navigator.ControllerChanger
import com.example.taximuslim.utils.permissions.PermissionConstants
import com.example.taximuslim.utils.permissions.PermissionManager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_maps_controller.*
import com.example.taximuslim.R
import com.example.taximuslim.data.network.dto.order.TariffRequest
import com.example.taximuslim.domain.order.models.TariffModel
import com.example.taximuslim.presentation.view.clientorder.managers.ButtonManager
import com.example.taximuslim.utils.mapfunc.PolyManager
import com.example.taximuslim.utils.prefference.getAuthHeader
import com.example.taximuslim.utils.view.ViewManager
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.auth_driver_public_offer_fragment.view.*
import kotlinx.android.synthetic.main.fragment_start.view.*
import kotlinx.android.synthetic.main.gradient_button.*


class MapsActivity : BaseActivity(), OnMapReadyCallback, View.OnClickListener,
    NavigationView.OnNavigationItemSelectedListener {

    companion object {
        const val EDIT_TEXT_TOP = "top"
        const val EDIT_TEXT_BOTTOM = "bottom"
        private const val MAP_ZOOM = 17f
    }


    lateinit var forFocusEditTextId: String
    var viewModel = MainViewModel()
    private lateinit var btnManager: ButtonManager
    private lateinit var mMap: GoogleMap
    private val permissionManager = PermissionManager(this)
    private var priceAlert: PriceAlert? = null
    private var commentAlert: CommentAlert? = null
    private val mapManger = FetchAddressIntentService(this)
    private val adapter = MapsCustomAdapter()
    private val controllerChanger = ControllerChanger(this)
    private val viewManager = ViewManager(this)
    private lateinit var polyManager: PolyManager
    var userLocation: String = ""
    var pointBLocation: String = ""
    var locationPrediction = LatLng(0.0, 0.0)
    private var userLocationLatLng = LatLng(0.0, 0.0)
    private var pointBLatLng = LatLng(0.0, 0.0)
    private lateinit var floatFragmentInstance: FloatFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        main_btn_text.text = getString(R.string.make_order)
        initViews()
        initViewModel()
        initNavigationDrawer()
        initMap()
        viewManager.hideViews(floatView)
    }

    override fun onStart() {
        super.onStart()
        user_location.clearFocus()
        pointBEditText.clearFocus()
        hideFloatView()
        viewManager.setOnFocusListener(pointBEditText) { openFloatView(EDIT_TEXT_BOTTOM) }
        viewManager.setOnFocusListener(user_location) { openFloatView(EDIT_TEXT_TOP) }
    }

    override fun layoutId() = R.layout.activity_maps_controller

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.economy_order -> {
                if (checkPointBEditText()) {
                    btnManager.checkEconomyBtnState()
                    btnManager.setPriceInPriceAlert(PriceHolder.economy)
                    showPriceAlertIfAlLeastOnButtonActive()
                    showPriceAndCommentEditTexts()
                }
            }
            R.id.comfort_order -> {
                if (checkPointBEditText()) {
                    btnManager.checkComfortBtnState()
                    btnManager.setPriceInPriceAlert(PriceHolder.comfort)
                    showPriceAlertIfAlLeastOnButtonActive()
                    showPriceAndCommentEditTexts()
                }
            }
            R.id.business_order -> {
                if (checkPointBEditText()) {
                    btnManager.checkBusinessBtnState()
                    btnManager.setPriceInPriceAlert(PriceHolder.business)
                    showPriceAlertIfAlLeastOnButtonActive()
                    showPriceAndCommentEditTexts()
                }
            }
            R.id.main_button_order_taxi -> { }
            R.id.burger_menu_main -> NavigationDrawerManager.showNavigationDrawer(drawer_layout)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.isCompassEnabled = false
        if (permissionManager.checkLocationPermissions()) updateLocation()
        polyManager = PolyManager(mMap)
        mMap.animateCamera(CameraUpdateFactory.zoomTo(MAP_ZOOM))
        mMap.setOnCameraChangeListener {
            Log.e("camera change listener:", it.toString())
            countScreenCenter()
           val location = mapManger.latLngToAddress(it.target)
            locationTextView.text = location


        }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_driver -> {
                showToast("Driver activity") //   controllerChanger.openMenuController("")
            }
            R.id.nav_guide ->
                controllerChanger.openMenuController(GuideFragment.FRAGMENT_ID)
            R.id.nav_history ->
                controllerChanger.openMenuController(HistoryFragment.FRAGMENT_ID)
            R.id.nav_settings ->
                controllerChanger.openMenuController(SettingsFragment.FRAGMENT_ID)
            R.id.nav_help ->
                controllerChanger.openMenuController(HelpFragment.FRAGMENT_ID)
        }
        return false
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PermissionConstants.LOCATION_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty())
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                        updateLocation()
            }
        }
    }

    private fun checkPointBEditText(): Boolean {
        pointBEditText.text?.isNotEmpty()?.let { isNotEmpty ->
            return isNotEmpty
        }
        return false
    }


    private fun openFloatView(forFocusEditTextId: String) {
        this@MapsActivity.forFocusEditTextId = forFocusEditTextId
        floatFragmentInstance = FloatFragment.newInstance()
        replaceFragment(floatFragmentInstance, R.id.floatView, FloatFragment.ID)
        viewManager.animViewUpToBottomAnim(floatView, 0f, 500)
        rootLayout.isClickable = false
        setOnFloatFragmentCloseListener()
    }

    fun hideFloatView() {
        viewManager.animViewUpToBottomAnim(floatView, 2500f, 500)
        rootLayout.isClickable = true
        removeFragment(FloatFragment.newInstance())
        viewManager.hideKeyBoard(floatView)
    }

    private fun initNavigationDrawer() =
        NavigationDrawerManager.navigationDrawerStateListener(drawer_layout)

    private fun initMap() {
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    fun initViews() {
        btnManager = ButtonManager(this)
        initList()
        economy_order.setOnClickListener(this)
        comfort_order.setOnClickListener(this)
        business_order.setOnClickListener(this)
        main_button_order_taxi.setOnClickListener(this)
        burger_menu_main.setOnClickListener(this)
        nav_view.setNavigationItemSelectedListener(this)
        priceAlert = PriceAlert(this as Activity)
        commentAlert = CommentAlert(
            this as Activity
        )
        commentAlert?.setOnCloseListener { _ ->
            viewManager.removeFocusFromEditTexts(tripPriceEditText, commentEditText)
        }

        priceAlert?.setOnCloseListener { _ ->
            viewManager.removeFocusFromEditTexts(tripPriceEditText, commentEditText)
        }
    }

    private fun updateLocation() = viewModel.loadLocation()


    private fun initViewModel() {
        viewModel.currentLocation.observe(this,
            Observer { location: Location ->
                val point = countPaddings()
                mMap.setPadding(0,0,0,point.y)
                Log.e("y:",point.y.toString())
                setUserMarker(location)
                moveCameraToTwoMarkers()
                locationPrediction = location.toLatLng()
                loadTarrifsByCountry(location)
                setUserLocationText(location)
            })

        PriceAlert.priceLiveData.observe(this, Observer { price ->
            if (price.isNotEmpty())
                tripPriceEditText.text = price
        })

        CommentAlert.toDriverCommentLiveData.observe(this, Observer { comment ->
            commentEditText.text = comment
        })

        viewModel.apply {
            placesForMapsView.observe(this@MapsActivity, Observer { places ->
                adapter.replaceAll(places)
            })

            pointBLiveData.observe(this@MapsActivity, Observer { address ->
                pointBEditText.text = address.toEditable()
                pointBLocation = address
                moveCameraToTwoMarkers()
            })

            tarriffsLiveData.observe(this@MapsActivity, Observer { tariffs ->
                setTarrifs(tariffs)
            })

            directionsLiveData.observe(this@MapsActivity, Observer { route ->
                polyManager.drawRoute(route, userLocationLatLng, pointBLatLng)
            })
        }
    }

    private fun setOnFloatFragmentCloseListener() {
        floatFragmentInstance.setOnCloseListener { pointB ->
            viewModel.loadRoutes(userLocation, pointB)
            pointBLatLng = mapManger.getLocationFromAddress(pointB)!!
        }
    }

    private fun setTarrifs(tariffs: TariffModel) {
        val economyText = "от " + tariffs.economy + " Rub"
        PriceHolder.economy = tariffs.economy
        firstCategoryPriceTextView.text = economyText

        val comfortText = "от " + tariffs.comfort + " Rub"
        PriceHolder.comfort = tariffs.comfort
        secondCategoryPriceTextView.text = comfortText

        val businessText = "от " + tariffs.business + " Rub"
        PriceHolder.business = tariffs.business
        thirdCategoryPriceTextView.text = businessText
    }

    private fun setUserLocationText(location: Location) {
        userLocationLatLng = location.toLatLng()
        userLocation = mapManger.latLngToAddress(location.toLatLng())
        user_location.text = SpannableStringBuilder(userLocation)
    }

    private fun loadTarrifsByCountry(location: Location) {
        mapManger.getCountry(location.toLatLng())?.let { tarrifs ->
            loadTarrifs(tarrifs)
        }
    }

    private fun setUserMarker(location: Location) {
        FetchAddressIntentService.markerUserLocation?.remove()
        FetchAddressIntentService.markerUserLocation =
            mMap.addMarker(
                MarkerOptions()
                    .position(location.toLatLng())
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.green_marker))
            )

        mMap.moveCamera(CameraUpdateFactory.newLatLng(location.toLatLng()))
        mMap.animateCamera(CameraUpdateFactory.zoomTo(MAP_ZOOM))
        mMap.setPadding(
            rootLayout.width / 18,
            0,
            rootLayout.width / 18,
            rootLayout.height / 2 * 1.5.toInt()
        )

        val projection = mMap.projection
        val currentPoint = projection.toScreenLocation(location.toLatLng())
        val nessesaryPoint = countScreenCenter()

    }

    fun moveCameraToTwoMarkers() {
        if (FetchAddressIntentService.markerPointBLocation.isNotEmpty()
            && FetchAddressIntentService.markerUserLocation.isNotEmpty()
        ) {
            mMap.animateCamera(mapManger.createCameraUpdateObject())
        }
    }

    private fun showPriceAlertIfAlLeastOnButtonActive() =
        viewManager.showPriceAlert(PriceAlert(this), btnManager)


    fun addMarkerOnPointB(address: String) =
        mapManger.getLocationFromAddress(address)?.let { location ->
            mapManger.addUserLocationMarkerAndMoveCameraToIt(
                mMap,
                location,
                MAP_ZOOM,
                R.drawable.green_marker
            )
        }

    private fun showPriceAndCommentEditTexts() {
        viewManager.showViews(
            tripPriceLayout,
            commentLayout,
            paymentKinTextView,
            paymentKindRadioGroup
        )
        viewManager.setOnFocusListener(tripPriceEditText) {
            viewManager.showPriceAlert(priceAlert, btnManager)
        }
        viewManager.setOnFocusListener(commentEditText) {
            viewManager.showAlert(commentAlert)
        }
    }

    private fun initList() {
        recyclerList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerList.adapter = adapter
        viewModel.loadPlaces()
    }

    private fun loadTarrifs(location: TariffRequest) {
        viewModel.loadTariffs(getAuthHeader(this), location)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (!rootLayout.isClickable)
            hideFloatView()
    }

    private fun countPaddings() : Point{
        val centerY =
             (resources.getDimension(R.dimen.dp12) + resources.getDimension(R.dimen.small_margin)
                    + createOrderView.height + recyclerList.height).toInt()


        val centerX = rootLayout.width / 2
        return Point(centerX,  centerY)
    }

    private fun countScreenCenter(): Point {
        val centerY =
            rootLayout.height - (rootLayout.height - (resources.getDimension(R.dimen.dp12) * 2 + createOrderView.height + recyclerList.height).toInt()) / 2
        val centerX = rootLayout.width / 2
        val projection = mMap.projection
        val point = Point(centerX, rootLayout.height - centerY)
        val a = projection.fromScreenLocation(point)
        Log.e("center location:", a.toString())
        return point
    }
}
