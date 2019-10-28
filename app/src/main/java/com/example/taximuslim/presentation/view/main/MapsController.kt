package com.example.taximuslim.presentation.view.main

import android.content.pm.PackageManager
import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taximuslim.R
import com.example.taximuslim.baseUI.controller.BaseController
import com.example.taximuslim.design.CommentDialogWindow
import com.example.taximuslim.design.PriceDialogWindow
import com.example.taximuslim.mapfunc.FetchAddressIntentService
import com.example.taximuslim.presentation.view.main.list.MapsCustomAdapter
import com.example.taximuslim.presentation.view.main.list.PlacesModel
import com.example.taximuslim.presenter.maps.MainPresenter
import com.example.taximuslim.utils.*
import com.example.taximuslim.utils.permissions.PermissionConstants
import com.example.taximuslim.utils.permissions.PermissionManager
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import kotlinx.android.synthetic.main.activity_maps_controller.*


//TODO Своевременный вывод price layout и alert dialog
class MapsController : BaseController(), OnMapReadyCallback, View.OnClickListener {

    private lateinit var mMap: GoogleMap
    private var presenter = MainPresenter()
    private val permissionManager = PermissionManager(this)
    private var priceDialogWindow: PriceDialogWindow? = null
    private var commentDialogWindow: CommentDialogWindow? = null
    private val mapManger = FetchAddressIntentService(this)
    private var btnManager: ButtonManager? = null
    private val adapter = MapsCustomAdapter()


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
            R.id.place_location -> showPriceAlertDialog()

            R.id.comment_text -> showCommentAlertDialog()

            R.id.burger_menu_main -> showBurgerMenu()
        }
    }

    private fun showBurgerMenu() {
        TODO("Show burger menu")
    }

    private fun showCommentAlertDialog() = commentDialogWindow?.show()

    override fun layoutId() = R.layout.activity_maps_controller

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        btnManager = ButtonManager(this)
        initViews()
        initPresenter()
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        if (permissionManager.checkLocationPermissions()) updateLocation()
    }

    override fun onStart() {
        super.onStart()
        create_order_view.clearFocus()
        user_location.clearFocus()
        place_location.clearFocus()
        comment_text.clearFocus()
    }

    fun initViews() {
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

        val activity = this
        priceDialogWindow = PriceDialogWindow(activity)
        commentDialogWindow = CommentDialogWindow(activity)

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
            override fun afterTextChanged(p0: Editable?) {
                showPriceAlertDialog()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                showPriceAlertDialog()
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                showPriceAlertDialog()
            }
        })

    private fun showPriceAlertDialog() = priceDialogWindow?.show()

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

    private fun initPresenter() {
        presenter.currentLocation.observe(this,
            androidx.lifecycle.Observer { location ->
                mapManger.addMarkerAndMoveCameraToIt(mMap, location.toLatLng(), 15f)
                user_location.text =
                    SpannableStringBuilder(mapManger.latLngToAddress(location.toLatLng()))
            })

        PriceDialogWindow.price.observe(this, Observer {
            your_price.text = it
        })

        presenter.placesForMapsView.observe(this, Observer {
            adapter.replaceAll(it)
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
            mapManger.addMarkerAndMoveCameraToIt(mMap, location, 24f)
        }


    private fun initList() {
        recycler_list.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recycler_list.adapter = adapter
        presenter.loadPlaces()

    }
}
