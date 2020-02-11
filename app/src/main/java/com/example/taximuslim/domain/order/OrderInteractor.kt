package com.example.taximuslim.domain.order

import com.example.taximuslim.App
import com.example.taximuslim.data.network.dto.order.TariffRequest
import com.example.taximuslim.data.repository.google.GoogleRepo
import com.example.taximuslim.data.repository.guide.GuideRepo
import com.example.taximuslim.data.repository.order.IOrderRepository
import com.example.taximuslim.data.repository.order.OrderRepo
import com.example.taximuslim.domain.models.google.Route
import com.example.taximuslim.domain.models.guide.GuideCategoryModel
import com.example.taximuslim.domain.models.guide.PlaceByLocationModel
import com.example.taximuslim.domain.models.guide.UserPlaceByLocationModel
import com.example.taximuslim.domain.order.models.OrderModel
import com.example.taximuslim.domain.order.models.StatusAndDrivers
import com.example.taximuslim.domain.order.models.TariffModel
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class OrderInteractor : IOrderInteractor {

    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var orderRepo: IOrderRepository
    private val googleRepo = GoogleRepo()
    private val guideRepo = GuideRepo()

    override fun getTariffies(
        location: TariffRequest,
        listener: ((TariffModel) -> Unit)
    ) {
        orderRepo.getTarrifs(location) { tarrifs ->
            listener.invoke(tarrifs)
        }
    }

    override fun getDirections(startAddress: String, endAddress: String, listener: ((Route) -> Unit)) {
        googleRepo.getDirections(startAddress, endAddress) { route ->
            listener.invoke(route)
        }
    }


    override suspend fun getCategories(listener: ((List<GuideCategoryModel>) -> Unit)) {
        guideRepo.getCategories { response ->
            listener.invoke(response)
        }
    }

    override suspend fun getPlaceByLocation(
        userPlaceByLocation: UserPlaceByLocationModel,
        listener: ((List<PlaceByLocationModel>) -> Unit)
    ) {
        guideRepo.getPlaceByLocation(userPlaceByLocation) { response ->
            listener.invoke(response)
        }
    }

    override suspend fun createOrder(order: OrderModel, listener: ((Int) -> Unit)) {
        orderRepo.createOrder(order) { tripId ->
            listener.invoke(tripId)
        }
    }

    override fun fetchOrderStatus(tripId: Int): Observable<StatusAndDrivers> {
        return orderRepo.fetchOrderStatus(tripId)
            .toObservable()
            .subscribeOn(Schedulers.io())
            .delay(5, TimeUnit.SECONDS)
            .repeat()


    }
}