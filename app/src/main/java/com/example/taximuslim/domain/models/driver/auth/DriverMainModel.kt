package com.example.taximuslim.domain.models.driver.auth

import com.example.taximuslim.data.network.remote.response.driver.CarMark
import com.example.taximuslim.data.network.remote.response.driver.CarModel

class DriverMainModel(
    var carColor: CarColor? = null,
    var carMark: CarMark? = null,
    var carModel: CarModel? = null
)