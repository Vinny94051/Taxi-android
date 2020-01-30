package com.example.taximuslim.data.network.remote.request.driver

data class UploadDriverImageRequest(
    val description: String,
    val image: String,
    val `where`: String
)