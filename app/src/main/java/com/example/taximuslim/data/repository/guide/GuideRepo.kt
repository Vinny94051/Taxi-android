package com.example.taximuslim.data.repository.guide

import android.content.Context
import android.util.Log
import com.example.taximuslim.App
import com.example.taximuslim.data.network.api.GuideApi
import com.example.taximuslim.data.network.dto.Token
import com.example.taximuslim.domain.models.guide.GuideCategoryModel
import com.example.taximuslim.domain.models.guide.PlaceByLocationModel
import com.example.taximuslim.domain.models.guide.UserPlaceByLocationModel
import com.example.taximuslim.utils.prefference.getAuthHeader
import java.lang.Exception
import javax.inject.Inject

class GuideRepo {

    init {
        App.appComponent.inject(this)
    }

    companion object {
        const val TAG: String = "guide repo:"
    }

    @Inject
    lateinit var api: GuideApi

    @Inject
    lateinit var token: Token

    suspend fun getCategories(listener: ((List<GuideCategoryModel>) -> Unit)) {
        try {
            val response = api.getCategories(token.token)
            listener.invoke(CategoryResponseMapper().mapFromEntity(response))
        } catch (ex: Exception) {
            Log.e(TAG, "getCategories")
            ex.printStackTrace()
        }
    }

    suspend fun getPlaceByLocation(
        userPlaceByLocation: UserPlaceByLocationModel,
        listener: ((List<PlaceByLocationModel>) -> Unit)
    ) {
        try {
            val response = api.getPlacesByLocation(
                token.token,
                UserPlaceByLocationMapper().mapFromEntity(userPlaceByLocation)
            )

            listener.invoke(PlaceByLocationMapper().mapFromEntity(response))

        } catch (ex: Exception) {
            Log.e(TAG, "getPlaceByLoc")
            ex.printStackTrace()
        }
    }

}