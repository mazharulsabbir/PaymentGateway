package com.mazharulsabbir.paymentgateway.data.model


import com.google.gson.annotations.SerializedName

data class SSLPaymentGateway(
    @SerializedName("desc")
    val desc: List<Desc>?,
    @SerializedName("directPaymentURL")
    val directPaymentURL: String?,
    @SerializedName("directPaymentURLBank")
    val directPaymentURLBank: String?,
    @SerializedName("directPaymentURLCard")
    val directPaymentURLCard: String?,
    @SerializedName("failedreason")
    val failedreason: String?,
    @SerializedName("GatewayPageURL")
    val gatewayPageURL: String?,
    @SerializedName("gw")
    val gw: Gw?,
    @SerializedName("is_direct_pay_enable")
    val isDirectPayEnable: String?,
    @SerializedName("redirectGatewayURL")
    val redirectGatewayURL: String?,
    @SerializedName("redirectGatewayURLFailed")
    val redirectGatewayURLFailed: String?,
    @SerializedName("sessionkey")
    val sessionkey: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("storeBanner")
    val storeBanner: String?,
    @SerializedName("storeLogo")
    val storeLogo: String?,
    @SerializedName("store_name")
    val storeName: String?
)