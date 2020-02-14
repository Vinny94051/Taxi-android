package com.example.taximuslim.presentation.view.mainscreen.menu.fragments.guide

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.taximuslim.R
import com.example.taximuslim.baseUI.fragment.BaseFragment
import com.example.taximuslim.baseUI.fragment.BaseFragmentCompanion
import com.example.taximuslim.domain.models.guide.PlaceByLocationModel
import com.example.taximuslim.presentation.view.mainscreen.MapsActivity
import com.example.taximuslim.presentation.view.mainscreen.menu.fragments.SharedViewModel
import com.example.taximuslim.utils.toDp
import kotlinx.android.synthetic.main.fragment_place_description_layout.*

class PlaceDescriptionFragment : BaseFragment(), View.OnClickListener {

    companion object : BaseFragmentCompanion() {
        override val ID: String
            get() = "PLACE_DESCRIPTION_FRAGMENT"

        override fun newInstance(): Fragment = PlaceDescriptionFragment()

        const val PLACE : String = "PLACE"
    }

    private lateinit var sharedViewModel: SharedViewModel
    private var placeAddress: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedViewModel = activity?.run { ViewModelProviders.of(this)[SharedViewModel::class.java] }
            ?: throw Exception("Invalid Activity")

        sharedViewModel.placeLiveData.observe(this, Observer<PlaceByLocationModel> { place ->
            fillFragment(place)
            placeAddress = place.address
        })
    }

    override fun onStart() {
        super.onStart()
        orderButton.setOnClickListener(this)
    }

    override fun layoutId(): Int = R.layout.fragment_place_description_layout
    override fun buttonText(): String = getString(R.string.order_taxi)

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.orderButton -> {
                if (placeAddress.isNotEmpty()) {
                    val intent = Intent(activity, MapsActivity::class.java)
                    intent.putExtra(PLACE, placeAddress)
                    startActivity(intent)
                    activity?.finish()
                }
            }
        }
    }

    private fun fillFragment(place: PlaceByLocationModel) {
        Glide.with(placeImageView.context)
            .load(place.imageUrl)
            .transform(RoundedCorners(4.toDp()))
            .into(placeImageView)

        placeTypeTextView.text = place.name
        descriptionTextView.text = place.text
        distanceTextView.text = "${place.distance} км"
        placeAddressTextView.text = place.address

    }
}