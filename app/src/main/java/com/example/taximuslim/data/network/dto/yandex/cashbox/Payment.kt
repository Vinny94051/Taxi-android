package com.example.taximuslim.data.network.dto.yandex.cashbox

import com.google.gson.annotations.SerializedName

data class PaymentRequest(
    @SerializedName("payment_token")
    val paymentToken : String,
    @SerializedName("value")
    val value : String
)

data class PaymentResponse(
    @SerializedName("id")
    val paymentId : String,
    @SerializedName("status")
    val status : String,
    @SerializedName("paid")
    val isPaid : Boolean,
    @SerializedName("amount")
    val amount : Amount,
    @SerializedName("confirmation")
    val confirmation : Confirmation,
    @SerializedName("created_at")
    val createdAt : String,
    @SerializedName("description")
    val description : String,
    @SerializedName("recipient")
    val recipient : Recipient,
    @SerializedName("balance")
    val pay : String
)


data class Amount(
    @SerializedName("value")
    val price : String,
    @SerializedName("currency")
    val currency : String
)

data class Confirmation(
    @SerializedName("type")
    val type : String,

    @SerializedName("confirmation_url")
    val url : String
)

data class Recipient(
    @SerializedName("account_id")
    val accId : String,
    @SerializedName("gateway_id")
    val gatewayId : String
)