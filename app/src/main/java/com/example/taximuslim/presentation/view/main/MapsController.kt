package com.example.taximuslim.presentation.view.main

import android.content.pm.PackageManager
import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import com.example.taximuslim.R
import com.example.taximuslim.baseUI.controller.BaseController
import com.example.taximuslim.design.PriceDialogWindow
import com.example.taximuslim.mapfunc.FetchAddressIntentService
import com.example.taximuslim.presenter.maps.MainPresenter
import com.example.taximuslim.utils.*
import com.example.taximuslim.utils.permissions.PermissionConstants
import com.example.taximuslim.utils.permissions.PermissionManager
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import kotlinx.android.synthetic.main.activity_maps_controller.*

class MapsController : BaseController(), OnMapReadyCallback, View.OnClickListener {

    companion object {
        private const val ECONOMY_BUTTON_NUMBER = 0
        private const val COMFORT_BUTTON_NUMBER = 1
        private const val BUSINESS_BUTTON_NUMBER = 2
    }

    private lateinit var mMap: GoogleMap
    private var buttonsStates = arrayOf(false, false, false)
    private var presenter = MainPresenter()
    private val permissionManager = PermissionManager(this)
    private var priceDialogWindow: PriceDialogWindow? = null
    private val mapManger = FetchAddressIntentService(this)

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.economy_order -> {
                setButtonView(economy_order, ECONOMY_BUTTON_NUMBER)
            }
            R.id.comfort_order -> {
                setButtonView(comfort_order, COMFORT_BUTTON_NUMBER)
            }
            R.id.business_order -> {
                setButtonView(business_order, BUSINESS_BUTTON_NUMBER)
            }
            R.id.main_button_order_taxi -> {

            }

            R.id.place_location -> showAlertDialog()
        }
    }

    override fun layoutId() = R.layout.activity_maps_controller

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        initPresenter()
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

//    private fun updatePrice() {
//        presenter.updatePrice()
//    }

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
        onFocusListener(your_price)
        setTextOnButtons()
        addYourPriceTextChangeListener()

        val activity = this
        priceDialogWindow = PriceDialogWindow(activity)

        place_location.onSubmitNext { showPriceEditText() }
    }

    private fun addYourPriceTextChangeListener() =
        your_price.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                showAlertDialog()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                showAlertDialog()
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                showAlertDialog()
            }
        })

    private fun showAlertDialog() {
        priceDialogWindow?.show()
        //  updatePrice()
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
            if (isFocus) {
                showAlertDialog()
                // updatePrice()
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


    private fun setButtonView(button: Button, arrayNumber: Int) {
        if (buttonsStates[arrayNumber]) {
            setDefaultState(button)
        } else {
            setActivateState(button)
        }
    }

    private fun setActivateState(button: Button) {
        //TODO Кнопка переходит в состояние активности
    }

    private fun setDefaultState(button: Button) {
        //TODO Кнопка переходит в стандартное состояние
    }

    private fun updateLocation() {
        presenter.updateLocation()
        mMap.isMyLocationEnabled = true
    }

    private fun initPresenter() {
        presenter.currentLocation.observe(this,
            androidx.lifecycle.Observer { location ->
                mapManger.addMarkerAndMoveCameraToIt(mMap, location.toLatLng(), 15f)
                user_location.text =
                    SpannableStringBuilder(mapManger.latLngToAddress(location.toLatLng()))
            })

//        presenter.priceLiveData.observe(this,
//            Observer { price ->
//                your_price.text = price
//            }
//        )

        PriceDialogWindow.price.observe(this, Observer {
            your_price.text = it
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
                if (grantResults.isNotEmpty()) {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        updateLocation()
                    }
                }
            }
        }
    }


}
