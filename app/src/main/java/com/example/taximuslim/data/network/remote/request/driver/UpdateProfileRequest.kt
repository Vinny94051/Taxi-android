package com.example.taximuslim.data.network.remote.request.driver

data class UpdateProfileRequest(
    val email: String,
    val name: String,
    val surname: String
)