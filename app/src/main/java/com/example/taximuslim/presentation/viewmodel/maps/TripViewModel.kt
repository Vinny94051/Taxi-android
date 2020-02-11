package com.example.taximuslim.presentation.viewmodel.maps

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.taximuslim.App
import com.example.taximuslim.baseUI.viewmodel.BaseViewModel
import com.example.taximuslim.domain.order.IOrderInteractor
import com.example.taximuslim.domain.order.models.StatusAndDrivers
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class TripViewModel : BaseViewModel() {

    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var interactor: IOrderInteractor


    private val _statusLiveData = MutableLiveData<StatusAndDrivers>()
    val statusLiveData: LiveData<StatusAndDrivers>
        get() = _statusLiveData


    fun fetchOrderStatus(tripId: Int) {
        addDisposable(
            interactor.fetchOrderStatus(tripId)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError { t ->
                    Log.e("viewModel:", t.message.toString())
                }
                .subscribe { response ->
                    _statusLiveData.value = response
                }

        )
    }
}