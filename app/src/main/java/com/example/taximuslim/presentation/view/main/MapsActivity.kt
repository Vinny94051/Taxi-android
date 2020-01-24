package com.example.taximuslim.presentation.view.main

import android.content.pm.PackageManager
import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taximuslim.baseUI.activivty.BaseActivity
import com.example.taximuslim.presentation.view.design.dialogswindow.CommentDialogWindow
import com.example.taximuslim.presentation.view.design.dialogswindow.PriceDialogWindow
import com.example.taximuslim.utils.mapfunc.FetchAddressIntentService
import com.example.taximuslim.presentation.view.menu.fragments.GuideFragment
import com.example.taximuslim.presentation.view.menu.fragments.HelpFragment
import com.example.taximuslim.presentation.view.main.list.MapsCustomAdapter
import com.example.taximuslim.presentation.view.main.managers.ButtonManager
import com.example.taximuslim.presentation.view.main.managers.NavigationDrawerManager
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
import android.content.Context
import android.view.inputmethod.InputMethodManager
import com.example.taximuslim.R
import kotlinx.android.synthetic.main.gradient_button.*


//TODO Своевременный вывод price layout и alert dialog
class MapsActivity : BaseActivity(), OnMapReadyCallback, View.OnClickListener,
    NavigationView.OnNavigationItemSelectedListener {
    private lateinit var mMap: GoogleMap
    private var presenter = MainViewModel()
    private val permissionManager = PermissionManager(this)
    private var priceDialogWindow: PriceDialogWindow? = null
    private var commentDialogWindow: CommentDialogWindow? = null
    private val mapManger = FetchAddressIntentService(this)
    private var btnManager: ButtonManager? = null
    private val adapter = MapsCustomAdapter()
    private val controllerChanger = ControllerChanger(this)

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.economy_order -> {
                btnManager?.apply {
                    this.checkEconomyBtnState()
                    this.economyPriceForDialog()
                }
                if (!your_price_layout.isVisible)
                    showPriceEditText()
            }
            R.id.comfort_order -> {
                btnManager?.apply {
                    this.checkComfortBtnState()
                    this.comfortPriceForDialog()
                }
                if (!your_price_layout.isVisible)
                    showPriceEditText()
            }
            R.id.business_order -> {
                btnManager?.apply {
                    this.checkBusinessBtnState()
                    this.businessPriceForDialog()
                }
                if (!your_price_layout.isVisible)
                    showPriceEditText()
            }
            R.id.main_button_order_taxi -> {
            }
            R.id.place_location -> openFloatView()
            R.id.comment_text -> {
            }
            R.id.burger_menu_main -> {
                NavigationDrawerManager.showNavigationDrawer(drawer_layout)
            }
        }
    }

    private fun openFloatView() {
        replaceFragment(FloatFragment.INSTANCE, R.id.floatView, FloatFragment.ID)
        floatView.visibility = View.VISIBLE
        floatView.animate().translationY(0f).duration = 500
        rootLayout.isClickable = false
    }

    fun hideFloatView() {
        floatView.animate().translationY(2000f).duration = 500
        rootLayout.isClickable = true
        supportFragmentManager.beginTransaction().remove(FloatFragment.INSTANCE).commit()
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(
            floatView.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }

    private fun showCommentAlertDialog() = commentDialogWindow?.show()

    override fun layoutId() = R.layout.activity_maps_controller

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        main_btn_text.text = "Заказать"
        initViews()
        initViewModel()
        initNavigationDrawer()
        initMap()
        floatView.visibility = View.GONE

    }

    override fun onStart() {
        super.onStart()
        create_order_view.clearFocus()
        user_location.clearFocus()
        place_location.clearFocus()
        comment_text.clearFocus()
        hideFloatView()
        setOnFocusListener(place_location)
    }


    private fun setOnFocusListener(editText: EditText) {
        editText.setOnFocusChangeListener { _, isOpen ->
            if (isOpen)
                openFloatView()
        }
    }


    private fun initNavigationDrawer() =
        NavigationDrawerManager.navigationDrawerStateListener(drawer_layout)

    private fun initMap() {
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        if (permissionManager.checkLocationPermissions()) updateLocation()
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_driver -> {
                showToast("Driver activity")
                //   controllerChanger.openMenuController("")
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

    fun initViews() {
        btnManager = ButtonManager(this)
        economy_order.setOnClickListener(this)
        comfort_order.setOnClickListener(this)
        business_order.setOnClickListener(this)
        main_button_order_taxi.setOnClickListener(this)
        place_location.setOnClickListener(this)
        comment_text.setOnClickListener(this)
        burger_menu_main.setOnClickListener(this)
        initList()
        onFocusListener(your_price)
        setTextOnButtons()
        addYourPriceTextChangeListener()
        nav_view.setNavigationItemSelectedListener(this)
        val activity = this
        priceDialogWindow = PriceDialogWindow(activity)
        commentDialogWindow =
            CommentDialogWindow(activity)

        place_location.onSubmitNext {
            btnManager?.let { btnManager_ ->
                if (btnManager_.isAtLeastOneBtnActive())
                    showPriceEditText()
            }
            addMarkerOnPointB(place_location.text.toString())
        }
    }

    private fun addYourPriceTextChangeListener() =
        your_price.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                showPriceAlertDialog()
            }
        })

    private fun showPriceAlertDialog() {
        priceDialogWindow?.show()
        your_price.clearFocus()
    }

    /**
     *Show edit text for entering price if all address fields not empty
     */
    private fun showPriceEditText() {
        if (place_location.text.toString() != "" &&
            user_location.text.toString() != ""
        ) {
            your_price_layout.visibility = View.VISIBLE
            your_price_layout.requestFocus()
        }
    }

    /**
     *Focus for showing window for entering price
     */
    private fun onFocusListener(editText: EditText) {
        editText.onFocusChangeListener = View.OnFocusChangeListener { view, isFocus ->
            if (isFocus && btnManager?.isAtLeastOneBtnActive()!!) {
                showPriceAlertDialog()
            }
        }
    }

    private fun setTextOnButtons() {
        createTextForButton(economy_order, R.string.economy_full)
        createTextForButton(comfort_order, R.string.comfort_full)
        createTextForButton(business_order, R.string.business_full)
    }

    private fun createTextForButton(btn: Button, id: Int) =
        btn.setSpannedText(getSpannedText(java.lang.String.format(resources.getString(id))))

    private fun updateLocation() {
        presenter.loadLocation()
        mMap.isMyLocationEnabled = true
    }

    var userLocation: String = ""

    private fun initViewModel() {
        presenter.currentLocation.observe(this,
            Observer { location ->
                mapManger.addMarkerAndMoveCameraToIt(
                    mMap,
                    location.toLatLng(),
                    15f,
                    R.drawable.green_marker
                )
                userLocation =
                    SpannableStringBuilder(mapManger.latLngToAddress(location.toLatLng())).toString()

                user_location.text =
                    SpannableStringBuilder(userLocation)
            })

        PriceDialogWindow.price.observe(this, Observer { price ->
            your_price.text = price
        })

        presenter.placesForMapsView.observe(this, Observer { places ->
            adapter.replaceAll(places)
        })
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

    private fun addMarkerOnPointB(address: String) =
        mapManger.getLocationFromAddress(this, address)?.let { location ->
            mapManger.addMarkerAndMoveCameraToIt(mMap, location, 15f, R.drawable.green_marker)
        }


    private fun initList() {
        recycler_list.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recycler_list.adapter = adapter
        presenter.loadPlaces()
    }


}
