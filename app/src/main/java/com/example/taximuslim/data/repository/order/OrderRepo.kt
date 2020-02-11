package com.example.taximuslim.data.repository.order

import android.util.Log
import com.example.taximuslim.App
import com.example.taximuslim.data.network.api.OrderAPi
import com.example.taximuslim.data.network.dto.Token
import com.example.taximuslim.data.network.dto.order.StatusRequest
import com.example.taximuslim.data.network.dto.order.TariffsResponse
import com.example.taximuslim.data.network.dto.order.TariffRequest
import com.example.taximuslim.domain.order.models.OrderModel
import com.example.taximuslim.domain.order.models.StatusAndDrivers
import com.example.taximuslim.domain.order.models.TariffModel
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class OrderRepo @Inject constructor(private var orderApi: OrderAPi) : IOrderRepository {

    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var token: Token

    override fun getTarrifs(
        tariffRequest: TariffRequest,
        listener: ((TariffModel) -> Unit)
    ) {
        orderApi.getTariffs(token.token, tariffRequest).enqueue(object : Callback<TariffsResponse> {
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

    override suspend fun createOrder(order: OrderModel, listener: (Int) -> Unit) {
        try {
            val result = orderApi.createOrder(token.token, MapperOrder().mapToEntity(order))
            Log.e("OrderRepo:", result.toString())
            listener.invoke(result.tripId)
        } catch (ex: Exception) {
            Log.e("OrderRepoFail", "puk puk")
            ex.printStackTrace()
        }

    }

    override fun fetchOrderStatus(tripId: Int): Single<StatusAndDrivers> =
        orderApi.fetchOrderStatus(token.token, StatusRequest(tripId)).map { response ->
            MapperOrderStatus().mapFromEntity(response)
        }

}