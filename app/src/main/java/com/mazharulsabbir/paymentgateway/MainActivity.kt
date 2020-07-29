package com.mazharulsabbir.paymentgateway

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mazharulsabbir.paymentgateway.data.api.SSLApiService
import com.mazharulsabbir.paymentgateway.data.model.SSLPaymentGateway
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val map = HashMap<String, String>()
        // store info
        map["store_id"] = SSL_STORE_ID
        map["store_passwd"] = SSL_STORE_PASSWORD

        // transaction info
        map["total_amount"] = "100"
        map["currency"] = "EUR"
        map["tran_id"] = "REF123"

        // customer info
        map["cus_name"] = "Customer Name"
        map["cus_email"] = "cust@yahoo.com"
        map["cus_city"] = "Dhaka"
        map["cus_country"] = "Bangladesh"
        map["cus_phone"] = "01711111111"
        map["cus_add"] = "Dhaka Bangladesh"

        // product info
        map["product_name"] = "SSL Payment Testing"
        map["product_category"] = "Developer"
        map["product_profile"] = "general"

        // shipping info
        map["shipping_method"] = "NO"
        val sslApiService = SSLApiService.create()
        sslApiService.getSSLGatewayResponse(
            // store info
            storeId = map["store_id"],
            storePassword = map["store_passwd"],

            // transaction info
            totalAmount = map["total_amount"].toString().toDouble(),
            currency = map["currency"],
            transactionId = map["tran_id"],

            // customer info
            cus_name = map["cus_name"],
            cus_email = map["cus_email"],
            cus_city = map["cus_city"],
            cus_country = map["cus_country"],
            cus_phone = map["cus_phone"],
            cus_add = map["cus_add"],

            // product info
            product_name = map["product_name"],
            product_category = map["product_category"],
            product_profile = map["product_profile"],

            // shipping info
            shipping_method = map["shipping_method"]
        ).enqueue(object : Callback<SSLPaymentGateway> {
            override fun onFailure(call: Call<SSLPaymentGateway>, t: Throwable) {
                t.printStackTrace()
                json_response.text = t.message.toString()
            }

            override fun onResponse(
                call: Call<SSLPaymentGateway>,
                response: Response<SSLPaymentGateway>
            ) {
                if (response.isSuccessful && response.code() == 200) {
                    json_response.text = response.body().toString()
                } else {
                    json_response.text = response.message()
                }
            }
        })
    }
}
