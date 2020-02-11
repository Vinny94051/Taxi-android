package com.example.taximuslim.data.repository.order


import com.example.taximuslim.data.network.dto.order.TariffRequest
import com.example.taximuslim.domain.order.models.OrderModel
import com.example.taximuslim.domain.order.models.StatusAndDrivers
import com.example.taximuslim.domain.order.models.TariffModel
import io.reactivex.Single


interface IOrderRepository {


    /**
     * @param tariffRequest - Object contains current city and country of user
     * @param listener - Emit response
     *
     * Function emit all tariffs available in tariffRequest region
     */
    fun getTarrifs(tariffRequest: TariffRequest, listener: ((TariffModel) -> Unit))

    /**
     * @param order - Object contains al field which need for order
     * @param listener - Emit trip id
     *
     * Function which create trip order
     */
    suspend fun createOrder(order: OrderModel, listener: (Int) -> Unit)


    /**
     * @param tripId - order id
     *
     * Return drivers status and list of drivers which accept order
     */
    fun fetchOrderStatus(tripId: Int) : Single<StatusAndDrivers>

}