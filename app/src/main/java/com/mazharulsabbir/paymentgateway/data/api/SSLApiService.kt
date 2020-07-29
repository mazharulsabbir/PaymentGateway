package com.mazharulsabbir.paymentgateway.data.api

import com.mazharulsabbir.paymentgateway.SSL_STORE_ID
import com.mazharulsabbir.paymentgateway.SSL_STORE_PASSWORD
import com.mazharulsabbir.paymentgateway.data.model.SSLPaymentGateway
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

private const val BASE_URL = "https://sandbox.sslcommerz.com"

interface SSLApiService {
    @FormUrlEncoded
    @POST("/gwprocess/v4/api.php")
    fun getSSLGatewayResponse(
        @Field("store_id") storeId: String? = SSL_STORE_ID,
        @Field("store_passwd") storePassword: String? = SSL_STORE_PASSWORD,
        @Field("total_amount") totalAmount: Double?,
        @Field("currency") currency: String?,
        @Field("tran_id") transactionId: String?,
        @Field("cus_name") cus_name: String?,
        @Field("cus_email") cus_email: String?,
        @Field("cus_city") cus_city: String?,
        @Field("cus_country") cus_country: String?,
        @Field("cus_add1") cus_add: String?,
        @Field("cus_phone") cus_phone: String?,
        @Field("product_name") product_name: String?,
        @Field("product_category") product_category: String?,
        @Field("product_profile") product_profile: String?,
        @Field("shipping_method") shipping_method: String? = "NO"
    ): Call<SSLPaymentGateway>

    companion object {
        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        fun create(): SSLApiService = retrofit.create(SSLApiService::class.java)
    }
}