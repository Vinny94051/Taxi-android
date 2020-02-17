package com.example.taximuslim.presentation.view.mainscreen

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Point
import android.location.Location
import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taximuslim.baseUI.activivty.BaseActivity
import com.example.taximuslim.presentation.view.design.dialogswindow.CommentAlert
import com.example.taximuslim.presentation.view.design.dialogswindow.PriceAlert
import com.example.taximuslim.utils.mapfunc.MapManager
import com.example.taximuslim.presentation.view.mainscreen.menu.fragments.guide.GuideFragment
import com.example.taximuslim.presentation.view.mainscreen.menu.fragments.HelpFragment
import com.example.taximuslim.presentation.view.adapter.guide.MainScreenGuideAdapter
import com.example.taximuslim.presentation.view.mainscreen.managers.NavigationDrawerManager
import com.example.taximuslim.presentation.view.mainscreen.menu.fragments.HistoryFragment
import com.example.taximuslim.presentation.view.mainscreen.menu.fragments.SettingsFragment
import com.example.taximuslim.presentation.viewmodel.maps.MainViewModel
import com.example.taximuslim.utils.*
import com.example.taximuslim.utils.navigator.ControllerChanger
import com.example.taximuslim.utils.permissions.PermissionConstants
import com.example.taximuslim.utils.permissions.PermissionManager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_maps_controller.*
import com.example.taximuslim.R
import com.example.taximuslim.data.network.dto.order.TariffRequest
import com.example.taximuslim.domain.models.guide.GuideCategoryModel
import com.example.taximuslim.domain.order.models.OrderModel
import com.example.taximuslim.domain.order.models.TariffModel
import com.example.taximuslim.presentation.view.driver.driverMainScreen.DriverMainScreen
import com.example.taximuslim.presentation.view.mainscreen.managers.ButtonManager
import com.example.taximuslim.presentation.view.mainscreen.menu.fragments.guide.PlaceDescriptionFragment.Companion.PLACE
import com.example.taximuslim.utils.mapfunc.PolyManager
import com.example.taximuslim.utils.view.ViewManager
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.gradient_button.*


class MapsActivity : BaseActivity(), OnMapReadyCallback, View.OnClickListener,
    NavigationView.OnNavigationItemSelectedListener {

    companion object {
        const val EDIT_TEXT_TOP = "top"
        const val EDIT_TEXT_BOTTOM = "bottom"
        const val MAP_ZOOM = 17f
    }


    lateinit var forFocusEditTextId: String
    var viewModel = MainViewModel()
    private lateinit var btnManager: ButtonManager
    private lateinit var mMap: GoogleMap
    private val permissionManager = PermissionManager(this)
    private var priceAlert: PriceAlert? = null
    private var commentAlert: CommentAlert? = null
    private val mapManger = MapManager(this)
    private val adapter =
        MainScreenGuideAdapter()
    private val controllerChanger = ControllerChanger(this)
    private val viewManager = ViewManager(this)
    private lateinit var polyManager: PolyManager
    var userLocation: String = ""
    var pointBLocation: String = ""
    var locationPrediction = LatLng(0.0, 0.0)
    private var userLocationLatLng = LatLng(0.0, 0.0)
    private var pointBLatLng = LatLng(0.0, 0.0)
    private lateinit var floatFragmentInstance: FloatFragment
    lateinit var placeAddress: String
    private var paymentType: Int = 0
    private var tarrif: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        hideActionBar()
        super.onCreate(savedInstanceState)
        main_btn_text.text = getString(R.string.make_order)
        initViews()
        initViewModel()
        initNavigationDrawer()
        initMap()
        viewManager.hideViews(floatView)
        placeAddress = intent.getStringExtra(PLACE) ?: "Empty"

    }

    override fun onStart() {
        super.onStart()
        user_location.clearFocus()
        pointBEditText.clearFocus()
        hideFloatView()
        viewManager.setOnFocusListener(pointBEditText) { openFloatView(EDIT_TEXT_BOTTOM) }
        viewManager.setOnFocusListener(user_location) { openFloatView(EDIT_TEXT_TOP) }
    }

    override fun onResume() {
        super.onResume()
        if (permissionManager.checkLocationPermissions()) updateLocation()
    }

    override fun layoutId() = R.layout.activity_maps_controller

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.economy_order -> {
                if (checkPointBEditText()) {
                    btnManager.checkEconomyBtnState()
                    btnManager.setPriceInPriceAlert(PriceHolder.economy, getCurrentPrice())
                    showPriceAlertIfAlLeastOnButtonActive()
                    showPriceAndCommentEditTexts()
                    tarrif = 1
                }
            }
            R.id.comfort_order -> {
                if (checkPointBEditText()) {
                    btnManager.checkComfortBtnState()
                    btnManager.setPriceInPriceAlert(PriceHolder.comfort, getCurrentPrice())
                    showPriceAlertIfAlLeastOnButtonActive()
                    showPriceAndCommentEditTexts()
                    tarrif = 2
                }
            }
            R.id.business_order -> {
                if (checkPointBEditText()) {
                    btnManager.checkBusinessBtnState()
                    btnManager.setPriceInPriceAlert(PriceHolder.business, getCurrentPrice())
                    showPriceAlertIfAlLeastOnButtonActive()
                    showPriceAndCommentEditTexts()
                    tarrif = 3
                }
            }
            R.id.main_button_order_taxi -> orderCar()
            R.id.burger_menu_main -> NavigationDrawerManager.showNavigationDrawer(drawer_layout)
            R.id.myLocationBtn -> updateLocation()
            R.id.cashRadioBtn -> {
                paymentType = 1
                main_button_order_taxi.setOnClickListener(this)
            }
            R.id.bankRadioBtn -> {
                paymentType = 2
                main_button_order_taxi.setOnClickListener(this)

            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.isCompassEnabled = false
        polyManager = PolyManager(mMap)
        mMap.animateCamera(CameraUpdateFactory.zoomTo(MAP_ZOOM))
        if (permissionManager.checkLocationPermissions()) updateLocation()
        mMap.setOnCameraChangeListener { cameraPosition ->
            Log.e("camera change listener:", cameraPosition.toString())
            countScreenCenter()
            if (cameraPosition.target != LatLng(0.0, 0.0)) {
                val location = mapManger.latLngToAddress(cameraPosition.target)
                locationTextView.text = location
                user_location.text = location.toEditable()
            }
        }

    }

    //TODO menu item activity не нужен
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_driver -> {
                val intent = Intent(this, DriverMainScreen::class.java)
                startActivity(intent)
                finish()
            }
            R.id.nav_guide ->
                controllerChanger.openMenuController(GuideFragment.FRAGMENT_ID)
            R.id.nav_history ->
                controllerChanger.openMenuController(HistoryFragment.FRAGMENT_ID)
            R.id.nav_settings ->
                controllerChanger.openMenuController(SettingsFragment.FRAGMENT_ID)
            R.id.nav_help ->
                controllerChanger.openMenuController(HelpFragment.ID)
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
        userLocation = user_location.text.toString()
        floatFragmentInstance = FloatFragment.newInstance()
        addFragment(floatFragmentInstance, R.id.floatView, FloatFragment.ID)
        viewManager.showViews(shadow, floatView)
        viewManager.animViewUpToBottomAnim(floatView, 0f, 500)
        rootLayout.isClickable = false
        setOnFloatFragmentCloseListener()
    }

    fun hideFloatView() {
        viewManager.hideViews(shadow)
        viewManager.animViewUpToBottomAnim(floatView, 2500f, 500)
        rootLayout.isClickable = true
        removeFragment(FloatFragment.newInstance())
        viewManager.hideKeyBoard(floatView)
        viewManager.hideViews(floatView)
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
        burger_menu_main.setOnClickListener(this)
        navView.setNavigationItemSelectedListener(this)
        myLocationBtn.setOnClickListener(this)
        cashRadioBtn.setOnClickListener(this)
        bankRadioBtn.setOnClickListener(this)
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
                setUserMarker(location)
                locationPrediction = location.toLatLng()
                loadTarrifsByCountry(location)
                setUserLocationText(location)
                if (placeAddress != "Empty") {
                    pointBLocation = placeAddress
                    pointBEditText.text = placeAddress.toEditable()
                    viewModel.loadRoutes(userLocation, placeAddress)
                    placeAddress = "Empty"
                    viewModel.loadLocation()
                }
            })

        PriceAlert.priceLiveData.observe(this, Observer { price ->
            if (price.isNotEmpty())
                tripPriceEditText.text = price
        })

        CommentAlert.toDriverCommentLiveData.observe(this, Observer { comment ->
            commentEditText.text = comment
        })

        viewModel.apply {

            pointBLiveData.observe(this@MapsActivity, Observer { address ->
                pointBEditText.text = address.toEditable()
                pointBLocation = address
            })

            tarriffsLiveData.observe(this@MapsActivity, Observer { tariffs ->
                setTarrifs(tariffs)
            })

            directionsLiveData.observe(this@MapsActivity, Observer { route ->
                userLocationLatLng =
                    mapManger.getLocationFromAddress(user_location.text.toString())
                viewManager.hideViews(locationTextView, userLocationMarker)
                mMap.setOnCameraChangeListener(null)
                polyManager.drawRoute(route)
            })

            guideCategoriesLiveData.observe(this@MapsActivity, Observer { places ->
                adapter.submitList(places)
            })

            tripIdLivedata.observe(this@MapsActivity, Observer { id ->
                tripId = id
                replaceFragment(
                    TripProcessFragment.newInstance(),
                    R.id.container,
                    TripProcessFragment.ID
                )
            })
        }


    }

    var tripId: Int = 0

    private fun setOnFloatFragmentCloseListener() {
        floatFragmentInstance.setOnCloseListener { pointB ->
            if (pointB.isNotEmpty()) {
                viewModel.loadRoutes(userLocation, pointB)
                pointBLatLng = mapManger.getLocationFromAddress(pointB)
            }
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
        mapManger.moveCameraToLocation(mMap, location.toLatLng(), MAP_ZOOM)
        mapManger.setPaddings(mMap, rootLayout)
    }


    private fun showPriceAlertIfAlLeastOnButtonActive() =
        viewManager.showPriceAlert(PriceAlert(this), btnManager, getCurrentPrice())


    private fun showPriceAndCommentEditTexts() {
        viewManager.showViews(
            tripPriceLayout,
            commentLayout,
            paymentKinTextView,
            paymentKindRadioGroup
        )
        viewManager.setOnFocusListener(tripPriceEditText) {
            viewManager.showPriceAlert(priceAlert, btnManager, getCurrentPrice())
        }
        viewManager.setOnFocusListener(commentEditText) {
            CommentHolder.comment = commentEditText.text ?: "".toEditable()
            viewManager.showAlert(commentAlert)
        }
    }

    private fun initList() {
        recyclerList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerList.adapter = adapter
        viewModel.loadGuideCategories()
        adapter.setOnItemClickListener { category: GuideCategoryModel ->
            controllerChanger.openMenuController(GuideFragment.FRAGMENT_ID, category.categoryId)
        }
    }

    private fun loadTarrifs(location: TariffRequest) = viewModel.loadTariffs(location)


    override fun onBackPressed() {
        when {
            !rootLayout.isClickable -> hideFloatView()
            supportFragmentManager.findFragmentByTag(TripEndFragment.ID) != null -> {
                supportFragmentManager.fragments.forEach { item ->
                    removeFragment(item)
                }
                finish()
                startActivity(intent)
            }
        }
        super.onBackPressed()
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

    private fun getCurrentPrice(): Editable =
        tripPriceEditText.text ?: "".toEditable()


    private fun createOrder(): OrderModel {
        return OrderModel(
            userLocation,
            userLocationLatLng.latitude,
            userLocationLatLng.longitude,
            pointBLocation,
            pointBLatLng.latitude,
            pointBLatLng.longitude,
            tariff = if (tarrif != 0) tarrif else throw Exception("Invalid Tariff"),
            price = getCurrentPrice().toString().replace(" Rub", "").toInt(),
            comment = if (CommentHolder.comment.isNotEmpty()) CommentHolder.comment.toString() else "",
            paymentType = if (paymentType != 0) paymentType else throw Exception("Invalid Payment Type")
        )
    }

    private fun orderCar() =
        viewModel.createOrder(createOrder())


}
