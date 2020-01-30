package com.example.taximuslim.presentation.view.clientorder

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taximuslim.App
import com.example.taximuslim.R
import com.example.taximuslim.baseUI.fragment.BaseFragment
import com.example.taximuslim.presentation.view.clientorder.list.prediction.PredictionAdapter
import com.example.taximuslim.utils.mapfunc.PlacePredictions
import com.example.taximuslim.utils.onSubmitNext
import com.example.taximuslim.utils.toEditable
import com.example.taximuslim.utils.view.ViewManager
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.net.PlacesClient
import kotlinx.android.synthetic.main.fragment_choose_address.*

class FloatFragment : BaseFragment(), View.OnClickListener {

    companion object {
        const val ID = "FLOAT_FRAGMENT"
        fun newInstance() = FloatFragment()

        const val DELAY: Long = 1250
    }

    lateinit var owner: MapsActivity
    private lateinit var viewManager: ViewManager
    private lateinit var placePredictions: PlacePredictions
    private var adapter = PredictionAdapter()
    private val handler = Handler()
    private var lastEditText: Long = 0


    private var address: String = ""

    override fun layoutId(): Int = R.layout.fragment_choose_address

    override fun onAttach(context: Context) {
        super.onAttach(context)
        owner = context as MapsActivity
        viewManager = ViewManager(context)
        placePredictions =
            PlacePredictions(initPlaces(context), (activity as MapsActivity).userLocation)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

    }

    override fun onResume() {
        super.onResume()
        requestFocus()
        userLocationEditText.text = (activity as MapsActivity).userLocation.toEditable()
        pointBLocationEditText.text = (activity as MapsActivity).pointBLocation.toEditable()
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.lineLayout -> closeFragment()
        }
    }

    private fun requestFocus() {
        when (owner.forFocusEditTextId) {
            MapsActivity.EDIT_TEXT_TOP -> {
                userLocationEditText.requestFocus()
                changeViewTint(userLocationCard, R.color.colorThemeGreen)
            }
            MapsActivity.EDIT_TEXT_BOTTOM -> {
                pointBLocationEditText.requestFocus()
            }
        }
    }

    private fun initViews() {
        lineLayout.setOnClickListener(this)
        initList()
        pointBLocationEditText.onSubmitNext { closeFragment() }

        userLocationEditText.addTextChangedListener { address ->
            addressInputAction(address)
        }
        pointBLocationEditText.addTextChangedListener { address ->
            owner.viewModel.pointBLiveData.value = address.toString()
            addressInputAction(address)
        }

        setEditTextsTintListeners()
    }

    private fun addressInputAction(address : Editable?){
        if (address != null) {
            if (address.isNotEmpty()) {
                lastEditText = System.currentTimeMillis()
                this@FloatFragment.address = address.toString()
                handler.postDelayed(inputFinishChecker, DELAY)
            }
        }
    }

    private val inputFinishChecker: Runnable = Runnable {
        if (System.currentTimeMillis() > (lastEditText + DELAY - 500)) {
            placePredictions.newInstance(
                (activity as MapsActivity).locationPrediction,
                address
            )
        }
    }

    private fun initList() {
        recyclerPredict.layoutManager = LinearLayoutManager(context)
        recyclerPredict.adapter = adapter

        placePredictions.setOnPredictionsListener { predictions ->
            adapter.submitList(predictions)
        }

        adapter.setOnItemClickListener {address ->
            pointBLocationEditText.text = address.toEditable()
            closeFragment()
        }
    }

    private fun setGreenTint(view: View) {
        changeViewTint(view, R.color.colorThemeGreen)
    }

    private fun hideGreenTint(view: View) {
        changeViewTint(view, android.R.color.transparent)
    }

    private fun setEditTextsTintListeners() {
        viewManager.setOnFocusListener(pointBLocationEditText) {
            setGreenTint(pointBLocationCard)
            hideGreenTint(userLocationCard)
        }

        viewManager.setOnFocusListener(userLocationEditText) {
            setGreenTint(userLocationCard)
            hideGreenTint(pointBLocationCard)
        }
    }

    private fun initPlaces(context: Context): PlacesClient {
        Places.initialize(context, App.API_KEY)
        return Places.createClient(context)
    }

    private fun closeFragment() {
        owner.hideFloatView()
        owner.addMarkerOnPointB(pointBLocationEditText.text.toString())
    }

}