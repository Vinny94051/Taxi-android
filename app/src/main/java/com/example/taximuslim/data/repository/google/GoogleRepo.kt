package com.example.taximuslim.data.repository.google

import android.util.Log
import com.example.taximuslim.App
import com.example.taximuslim.data.network.api.GoogleMapApi
import com.example.taximuslim.data.network.dto.google.directions.DirectionsResponse
import com.example.taximuslim.domain.models.google.Route
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class GoogleRepo {

    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var api: GoogleMapApi

    private val job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)


    fun getDirections(start: String, end: String, listener: ((Route) -> Unit)) {
        api.getDirection(start, end, App.API_KEY_DIRECTIONS)
            .enqueue(object : Callback<DirectionsResponse> {
                override fun onFailure(call: Call<DirectionsResponse>, t: Throwable) {
                    Log.e("directions:", t.message.toString())
                }

                override fun onResponse(
                    call: Call<DirectionsResponse>,
                    response: Response<DirectionsResponse>
                ) {
                    if (response.isSuccessful)
                        response.body()?.let { result ->
                            listener.invoke(MapperDirections().mapFromEntity(result))
                        }
                }
            })
    }
}

