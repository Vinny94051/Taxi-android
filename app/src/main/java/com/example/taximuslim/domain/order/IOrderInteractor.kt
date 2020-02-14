package com.example.taximuslim.domain.order

import com.example.taximuslim.data.network.dto.order.TariffRequest
import com.example.taximuslim.domain.models.google.Route
import com.example.taximuslim.domain.models.guide.GuideCategoryModel
import com.example.taximuslim.domain.models.guide.PlaceByLocationModel
import com.example.taximuslim.domain.models.guide.UserPlaceByLocationModel
import com.example.taximuslim.domain.order.models.BooleanStatus
import com.example.taximuslim.domain.order.models.OrderModel
import com.example.taximuslim.domain.order.models.StatusAndDrivers
import com.example.taximuslim.domain.order.models.TariffModel
import io.reactivex.Observable
import io.reactivex.Single

interface IOrderInteractor {
    fun getTariffies(location: TariffRequest, listener: ((TariffModel) -> Unit))
    fun getDirections(startAddress : String, endAddress : String, listener : ((Route) -> Unit) )
    suspend fun getCategories(listener: ((List<GuideCategoryModel>) -> Unit))
    suspend fun getPlaceByLocation(
        userPlaceByLocation: UserPlaceByLocationModel,
        listener: ((List<PlaceByLocationModel>) -> Unit)
    )

    suspend fun createOrder(order: OrderModel, listener: ((Int) -> Unit))

    fun fetchOrderStatus(tripId: Int): Observable<StatusAndDrivers>

    fun cancelOrder(tripId: Int): Single<BooleanStatus>

    fun chooseDriver(tripId: Int, driverId : Int) : Single<BooleanStatus>
}