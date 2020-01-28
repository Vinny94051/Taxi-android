package com.example.taximuslim.data.repository.order

import android.util.Log
import com.example.taximuslim.App
import com.example.taximuslim.data.network.api.OrderAPi
import com.example.taximuslim.data.network.dto.order.TariffsResponse
import com.example.taximuslim.data.network.dto.order.TariffRequest
import com.example.taximuslim.domain.order.models.TariffModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class OrderRepo {

    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var api: OrderAPi

    fun getTarrifs(
        token: String,
        tariffRequest: TariffRequest,
        listener: ((TariffModel) -> Unit)
    ) {
        api.getTariffs(token, tariffRequest).enqueue(object : Callback<TariffsResponse> {
            override fun onFailure(call: Call<TariffsResponse>, t: Throwable) {
                Log.e("orderRepo:", t.message.toString())
            }

            override fun onResponse(
                call: Call<TariffsResponse>,
                response: Response<TariffsResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { _response ->
                        listener.invoke(MapperTariffs().mapFromEntity(_response))
                    }
                }
            }
        })
    }

}