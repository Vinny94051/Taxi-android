package com.example.taximuslim.data.network.remote.response.driver


import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    val car: String,
    @SerializedName("car_color")
    val carColor: String,
    @SerializedName("car_image_photo")
    val carImagePhoto: String,
    @SerializedName("car_image_registration_certificate")
    val carImageRegistrationCertificate: String,
    @SerializedName("car_model")
    val carModel: String,
    @SerializedName("car_number")
    val carNumber: String,
    @SerializedName("client_id_trip")
    val clientIdTrip: Any?,
    @SerializedName("client_name")
    val clientName: String,
    @SerializedName("client_trip_status")
    val clientTripStatus: Any?,
    @SerializedName("document_image_back")
    val documentImageBack: String,
    @SerializedName("document_image_font")
    val documentImageFont: String,
    @SerializedName("document_number")
    val documentNumber: String,
    val driver: Boolean,
    @SerializedName("driver_id_trip")
    val driverIdTrip: Int,
    @SerializedName("driver_name")
    val driverName: String,
    @SerializedName("driver_trip_status")
    val driverTripStatus: Any?,
    val email: String,
    val image: String,
    @SerializedName("image_license_back")
    val imageLicenseBack: String,
    @SerializedName("image_license_font")
    val imageLicenseFont: String,
    @SerializedName("moderation_client")
    val moderationClient: String,
    @SerializedName("moderation_driver")
    val moderationDriver: String,
    val phone: String,
    val profile: String,
    val surname: String
)