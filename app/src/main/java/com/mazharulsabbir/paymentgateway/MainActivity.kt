package com.mazharulsabbir.paymentgateway

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
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

        supportActionBar?.title = "Make Payment With"
    }

    override fun onResume() {
        super.onResume()
        getPaymentMethods()
    }

    private fun getPaymentMethods() {
        val map = HashMap<String, String>()
        // store info
        map["store_id"] = SSL_STORE_ID
        map["store_passwd"] = SSL_STORE_PASSWORD

        // transaction info
        map["total_amount"] = "100"
        map["currency"] = "EUR"
        map["tran_id"] = "REF123"

        // redirect url
        map["success_url"] = "http://localhost/new_sslcz_gw/success.php"
        map["fail_url"] = "http://localhost/new_sslcz_gw/fail.php"
        map["cancel_url"] = "http://localhost/new_sslcz_gw/cancel.php"

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

            // redirect url's
            success_url = map["success_url"],
            fail_url = map["fail_url"],
            cancel_url = map["cancel_url"],

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
                t.localizedMessage?.let {
                    Snackbar.make(
                        recycler_view.rootView,
                        it,
                        Snackbar.LENGTH_SHORT
                    )
                        .show()
                }

            }

            override fun onResponse(
                call: Call<SSLPaymentGateway>,
                response: Response<SSLPaymentGateway>
            ) {
                if (response.isSuccessful && response.code() == 200) {
                    response.body().let {
                        it?.desc?.let { desc ->
                            val paymentMethodsAdapter = PaymentMethodsAdapter()
                            paymentMethodsAdapter.submitList(desc)

                            paymentMethodsAdapter.onItemClick(object : OnItemClickListener {
                                override fun onItemClick(p: Int) {
                                    val url = desc[p].redirectGatewayURL
                                    val intent =
                                        Intent(applicationContext, PaymentWithSSL::class.java)
                                    intent.putExtra(EXTRA_GATEWAY_URL, url)
                                    startActivity(intent)
                                }
                            })
                            recycler_view.apply {
                                hasFixedSize()
                                this.adapter = paymentMethodsAdapter
                                this.layoutManager = GridLayoutManager(context, 3)
                            }
                        }
                    }
                } else {
                    Snackbar.make(recycler_view.rootView, response.message(), Snackbar.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }
}
