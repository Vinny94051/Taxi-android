package com.example.taximuslim.domain.models.driver



data class ProfileModel(
    val car: String,
    val carColor: String,
    val carImagePhoto: String,
    val carImageRegistrationCertificate: String,
    val carModel: String,
    val carNumber: String,
    val clientIdTrip: Any?,
    val clientName: String,
    val clientTripStatus: Any?,
    val documentImageBack: String,
    val documentImageFont: String,
    val documentNumber: String,
    val driver: Boolean,
    val driverIdTrip: Int,
    val driverName: String,
    val driverTripStatus: Any?,
    val email: String,
    val image: String,
    val imageLicenseBack: String,
    val imageLicenseFont: String,
    val moderationClient: String,
    val moderationDriver: String,
    val phone: String,
    val profile: String,
    val surname: String
)