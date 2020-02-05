package com.example.taximuslim.data.network.remote.request.driver

data class UploadDriverImageRequest(
    val `where`: String,
    val description: String,
    val image: String

)