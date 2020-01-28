package com.example.taximuslim.utils.mapfunc

import android.util.Log
import com.example.taximuslim.presentation.view.clientorder.list.prediction.PredictionModel
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.model.RectangularBounds
import com.google.android.libraries.places.api.model.TypeFilter
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.gms.common.api.ApiException


class PlacePredictions(var placesClient: PlacesClient, var currentUserLocation: String) {
    val token = AutocompleteSessionToken.newInstance()

    private val distanceCalculator = DistanceCalculator()

    private var predictionsListener: ((List<PredictionModel>) -> Unit)? = null
    fun setOnPredictionsListener(listener: ((List<PredictionModel>) -> Unit)) {
        predictionsListener = listener
    }


    fun newInstance(location: LatLng, userInput: String) {
        val bounds = RectangularBounds.newInstance(
            location, location
        )

        val request = FindAutocompletePredictionsRequest.builder()
            .setLocationBias(bounds)
            .setTypeFilter(TypeFilter.ADDRESS)
            .setSessionToken(token)
            .setQuery(userInput)
            .build()

        placesClient.findAutocompletePredictions(request)
            .addOnSuccessListener { response ->
                predictionsListener?.invoke(
                    response.autocompletePredictions.map { prediction ->
                        PredictionModel(
                            prediction.getPrimaryText(null).toString(),
                            prediction.getSecondaryText(null).toString(),
                            distanceCalculator
                                .calculateDistance(
                                    currentUserLocation,
                                    prediction.getFullText(null).toString()
                                )
                        )
                    }
                )

            }.addOnFailureListener { exception ->
                val apiException = exception as ApiException
                Log.e("ERROR:::", "Place not found: " + apiException.statusCode)
            }
    }
}