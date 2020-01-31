package com.example.taximuslim.presentation.view.clientorder

import android.app.Activity
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.text.SpannableStringBuilder
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
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_maps_controller.*
import com.example.taximuslim.R
import com.example.taximuslim.data.network.dto.order.TariffRequest
import com.example.taximuslim.presentation.view.clientorder.managers.ButtonManager
import com.example.taximuslim.utils.prefference.getAuthHeader
import com.example.taximuslim.utils.view.ViewManager
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.gradient_button.*


//TODO Своевременный вывод priceLiveData layout и alert dialog
class MapsActivity : BaseActivity(), OnMapReadyCallback, View.OnClickListener,
    NavigationView.OnNavigationItemSelectedListener {

    companion object {
        const val EDIT_TEXT_TOP = "top"
        const val EDIT_TEXT_BOTTOM = "bottom"
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
            R.id.main_button_order_taxi -> {
            }
            R.id.pointBEditText -> {
            }
            R.id.commentEditText -> {
            }
            R.id.burger_menu_main -> NavigationDrawerManager.showNavigationDrawer(drawer_layout)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        if (permissionManager.checkLocationPermissions()) updateLocation()
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
        replaceFragment(FloatFragment.newInstance(), R.id.floatView, FloatFragment.ID)
        viewManager.animViewUpToBottomAnim(floatView, 0f, 500)
        rootLayout.isClickable = false
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
        pointBEditText.setOnClickListener(this)
        commentEditText.setOnClickListener(this)
        burger_menu_main.setOnClickListener(this)
        nav_view.setNavigationItemSelectedListener(this)
        priceAlert = PriceAlert(this as Activity)
        commentAlert = CommentAlert(this as Activity)
        commentAlert?.setOnCloseListener { _ ->
            viewManager.removeFocusFromEditTexts(tripPriceEditText, commentEditText)
        }

        priceAlert?.setOnCloseListener { _ ->
            viewManager.removeFocusFromEditTexts(tripPriceEditText, commentEditText)
        }
    }

    private fun updateLocation() {
        viewModel.loadLocation()
        mMap.isMyLocationEnabled = true
    }

    var userLocation: String = ""
    var pointBLocation: String = ""
    var locationPrediction = LatLng(0.0, 0.0)

    private fun initViewModel() {
        viewModel.currentLocation.observe(this,
            Observer { location: Location ->
                FetchAddressIntentService.markerUserLocation?.remove()
                FetchAddressIntentService.markerUserLocation =
                    mapManger.addUserLocationMarkerAndMoveCameraToIt(
                        mMap, location.toLatLng(),
                        15f, R.drawable.green_marker
                    )

                locationPrediction = location.toLatLng()

                mapManger.getCountry(location.toLatLng())?.let {
                    loadTarrifs(it)
                }
                userLocation = mapManger.latLngToAddress(location.toLatLng())

                user_location.text = SpannableStringBuilder(userLocation)
            })

        PriceAlert.priceLiveData.observe(this, Observer { price ->
            if (price.isNotEmpty())
                tripPriceEditText.text = price
        })

        CommentAlert.toDriverCommentLiveData.observe(this, Observer { comment ->
            commentEditText.text = comment
        })

        viewModel.placesForMapsView.observe(this, Observer { places ->
            adapter.replaceAll(places)
        })

        viewModel.pointBLiveData.observe(this, Observer { address ->
            pointBEditText.text = address.toEditable()
            pointBLocation = address
        })

        viewModel.tarriffsLiveData.observe(this, Observer { tariffs ->
            val economyText = "от " + tariffs.economy + " Rub"
            PriceHolder.economy = tariffs.economy
            firstCategoryPriceTextView.text = economyText

            val comfortText = "от " + tariffs.comfort + " Rub"
            PriceHolder.comfort = tariffs.comfort
            secondCategoryPriceTextView.text = comfortText

            val businessText = "от " + tariffs.business + " Rub"
            PriceHolder.business = tariffs.business
            thirdCategoryPriceTextView.text = businessText
        })
    }

    private fun showPriceAlertIfAlLeastOnButtonActive() =
        viewManager.showPriceAlert(PriceAlert(this), btnManager)


    fun addMarkerOnPointB(address: String) =
        mapManger.getLocationFromAddress(address)?.let { location ->
            mapManger.addUserLocationMarkerAndMoveCameraToIt(
                mMap,
                location,
                15f,
                R.drawable.green_marker
            )
        }

    private fun showPriceAndCommentEditTexts() {
        viewManager.showViews(tripPriceLayout, commentLayout, paymentKinTextView, paymentKindRadioGroup)
        viewManager.setOnFocusListener(tripPriceEditText) {
            viewManager.showPriceAlert(priceAlert, btnManager)
        }
        viewManager.setOnFocusListener(commentEditText) {
            viewManager.showAlert(commentAlert)
        }
    }

    private fun initList() {
        recycler_list.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recycler_list.adapter = adapter
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

}
