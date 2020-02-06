package com.example.taximuslim.presentation.view.mainscreen

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taximuslim.App
import com.example.taximuslim.R
import com.example.taximuslim.baseUI.fragment.BaseFragment
import com.example.taximuslim.presentation.view.adapter.prediction.PredictionAdapter
import com.example.taximuslim.utils.cursorToEnd
import com.example.taximuslim.utils.mapfunc.MapManager
import com.example.taximuslim.utils.mapfunc.PlacePredictions
import com.example.taximuslim.utils.onSubmitNext
import com.example.taximuslim.utils.toEditable
import com.example.taximuslim.utils.toLocation
import com.example.taximuslim.utils.view.ViewManager
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxbinding2.widget.textChanges
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_float.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class FloatFragment : BaseFragment(), View.OnClickListener {

    init {
        App.appComponent.inject(this)
    }

    companion object {
        const val ID = "FLOAT_FRAGMENT"
        fun newInstance() = FloatFragment()

    }

    @Inject
    lateinit var addressServiece: MapManager

    private lateinit var owner: MapsActivity
    private lateinit var viewManager: ViewManager
    private lateinit var placePredictions: PlacePredictions
    private var adapter = PredictionAdapter()

    var closeListener: ((String) -> Unit)? = null


    override fun layoutId(): Int = R.layout.fragment_float

    override fun onAttach(context: Context) {
        super.onAttach(context)
        owner = context as MapsActivity
        viewManager = ViewManager(context)
    }


    override fun onResume() {
        super.onResume()
        requestFocus()
        userLocationEditText.text = (activity as MapsActivity).userLocation.toEditable()
        pointBLocationEditText.text = (activity as MapsActivity).pointBLocation.toEditable()
        placePredictions =
            PlacePredictions(initPlaces(owner), (activity as MapsActivity).userLocation)
        initViews()
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.lineLayout -> closeFragment(1)
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
        setOnPredictionsListener()
        pointBLocationEditText.onSubmitNext {
            searchAction(pointBLocationEditText.text.toString(), 2)
        }

        userLocationEditText.cursorToEnd()
        pointBLocationEditText.cursorToEnd()

        setEditTextDebounce(userLocationEditText, 0)
        setEditTextDebounce(pointBLocationEditText, 1)
        setEditTextsTintListeners()
    }

    private fun setEditTextDebounce(editText: EditText, action: Int) = editText
        .textChanges()
        .skip(1)
        .map { address -> address.toString() }
        .doOnNext {
            mainProgressbar.visibility = View.VISIBLE
            recyclerPredict.visibility = View.GONE
        }
        .debounce(900, TimeUnit.MILLISECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .doOnEach {
            mainProgressbar.visibility = View.GONE
            recyclerPredict.visibility = View.VISIBLE
        }
        .doOnError {
            Snackbar.make(
                pointBLocationCard,
                "Error while searching",
                Snackbar.LENGTH_SHORT
            ).show()
        }
        .retry()
        .subscribe({ address ->
            searchAction(address, action)
        }, { exception ->
            Log.e("FloatFragment", exception.toString())
        })

    private fun searchAction(address: String, action: Int) {
        when (action) {
            1 -> owner.viewModel.pointBLiveData.value = address
        }
        placePredictions.newInstance(
            (activity as MapsActivity).locationPrediction,
            address
        )
    }

    private fun initList() {
        recyclerPredict.layoutManager = LinearLayoutManager(context)
        recyclerPredict.adapter = adapter

        adapter.setOnItemClickListener { address ->
            if (pointBLocationEditText.isFocused) {
                pointBLocationEditText.text = address.toEditable()
                closeFragment(1)
            } else if (userLocationEditText.isFocused) {
                setUserLocation(address)
                closeFragment(2)
            }
        }
    }

    private fun setUserLocation(address: String) {
        addressServiece.getLocationFromAddress(address)?.toLocation()?.let { userLoc ->
            owner.viewModel.setLocation(userLoc)
        }
    }

    private fun setOnPredictionsListener() =
        placePredictions.setOnPredictionsListener { predictions ->
            adapter.submitList(predictions)
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

    fun setOnCloseListener(listener: ((String) -> Unit)) {
        closeListener = listener
    }

    private fun closeFragment(markerNumber: Int) {
        owner.hideFloatView()
        when (markerNumber) {
            1 -> {
            }
            2 -> setUserLocation(userLocationEditText.text.toString())
        }
        closeListener?.invoke(pointBLocationEditText.text.toString())
    }


}