package com.example.taximuslim.data.network.dto.driver

import com.example.taximuslim.data.network.remote.response.driver.OrderResponse

data class OrderListResponse(
    val ordersList : List<OrderResponse>? = listOf()
)