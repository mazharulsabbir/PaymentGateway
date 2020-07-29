package com.mazharulsabbir.paymentgateway.data.model


import com.google.gson.annotations.SerializedName

data class Desc(
    @SerializedName("gw")
    val gw: String?,
    @SerializedName("logo")
    val logo: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("r_flag")
    val rFlag: String?,
    @SerializedName("redirectGatewayURL")
    val redirectGatewayURL: String?,
    @SerializedName("type")
    val type: String?
)