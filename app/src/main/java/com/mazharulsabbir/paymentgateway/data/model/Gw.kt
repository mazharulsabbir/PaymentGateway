package com.mazharulsabbir.paymentgateway.data.model


import com.google.gson.annotations.SerializedName

data class Gw(
    @SerializedName("amex")
    val amex: String?,
    @SerializedName("internetbanking")
    val internetbanking: String?,
    @SerializedName("master")
    val master: String?,
    @SerializedName("mobilebanking")
    val mobilebanking: String?,
    @SerializedName("othercards")
    val othercards: String?,
    @SerializedName("visa")
    val visa: String?
)