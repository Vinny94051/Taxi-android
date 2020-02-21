package com.example.taximuslim.presentation.view.driver.driverOrders

import android.animation.ObjectAnimator
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.TransitionDrawable
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taximuslim.App

import com.example.taximuslim.R
import com.example.taximuslim.data.network.dto.driver.DriverLocation
import com.example.taximuslim.data.network.dto.order.OrderRequest
import com.example.taximuslim.databinding.DriverOrderFragmentBinding
import com.example.taximuslim.presentation.view.adapter.DriverOrderAdapter
import com.example.taximuslim.presentation.view.baseFragment.ObservableFragment
import com.example.taximuslim.utils.location.IUserLocationProvider
import com.example.taximuslim.utils.toDp
import kotlinx.android.synthetic.main.activity_driver_main_screen.*
import kotlinx.android.synthetic.main.driver_order_fragment.*
import javax.inject.Inject

class DriverOrderFragment : ObservableFragment() {


    init {
        App.appComponent.inject(this)
    }

    private lateinit var viewModel: DriverOrderViewModel
    private lateinit var binding: DriverOrderFragmentBinding
    private val adapter = DriverOrderAdapter()

    @Inject
    lateinit var userLocationProvider: IUserLocationProvider

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(DriverOrderViewModel::class.java)
        binding = DriverOrderFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        initTougle()
        initList()
        userLocationProvider.getLocation { location ->
            viewModel.fetchTripList(
                DriverLocation(
                    location?.latitude.toString(),
                    location?.longitude.toString()
                )
            )
        }
    }

    override fun onResume() {
        super.onResume()
        (strokeTougle.background as GradientDrawable).setStroke(
            2.toDp(),
            resources.getColor(R.color.red)
        )
    }

    override fun setUIState() {
        (activity as AppCompatActivity).supportActionBar?.hide()
        (activity as AppCompatActivity).drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        (activity as AppCompatActivity).burgerButton.visibility = View.VISIBLE
    }

    override fun setObservers() {
        viewModel.orderToDriverModelListLiveData.observe(this, Observer { response ->
            Log.e("REE::", response.toString())
            if (response.isNotEmpty()) ordersPlaceHolderTextView.visibility = View.GONE
            adapter.submitList(response)
        })
    }

    private fun initList() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }

    private fun initTougle() {

        stateButton.setOnClickListener {

            val transitionBack: TransitionDrawable = stateButton.background as TransitionDrawable
            val transitionStroke: GradientDrawable = strokeTougle.background as GradientDrawable
            var state = true


            val scale1 = ObjectAnimator.ofFloat(
                stateButton,
                "translationX",
                (strokeTougle.width - stateButton.width - 8.toDp()
                        - (shadowButton.width - stateButton.width)).toFloat()
            )
            scale1.interpolator = FastOutSlowInInterpolator()
            val scale2 = ObjectAnimator.ofFloat(stateButton, "translationX", 0.0f)
            scale2.interpolator = FastOutSlowInInterpolator()

            stateButton.setOnClickListener {
                if (state) {
                    transitionBack.reverseTransition(200)
                    transitionStroke.setStroke(
                        2.toDp(),
                        resources.getColor(R.color.colorThemeGreen)
                    )
                    stateButton.text = "Свободен"
                    shadow.visibility = View.GONE
                    scale1.start()
                    state = false
                } else {
                    transitionBack.reverseTransition(200)
                    transitionStroke.setStroke(2.toDp(), resources.getColor(R.color.red))
                    stateButton.text = "Занят"
                    scale2.start()
                    shadow.visibility = View.VISIBLE
                    state = true
                }
            }
            stateButton.performClick()
        }

    }
}
