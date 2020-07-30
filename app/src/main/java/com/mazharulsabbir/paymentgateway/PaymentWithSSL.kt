package com.mazharulsabbir.paymentgateway

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_payment_with_s_s_l.*
import java.util.logging.Logger

const val EXTRA_GATEWAY_URL = "com.mazharulsabbir.paymentgateway.EXTRA_URL"
private const val TAG = "PaymentWithSSL"

class PaymentWithSSL : AppCompatActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_with_s_s_l)

        supportActionBar?.title = "Confirm Payment"

        web_view.webViewClient = MyWebViewClient(this)
        val webSettings = web_view.settings
        webSettings.javaScriptEnabled = true

        intent.getStringExtra(EXTRA_GATEWAY_URL)?.let {
            web_view.loadUrl(it)
            Logger.getLogger(TAG).warning("url====$it")
        }
    }
}

class MyWebViewClient internal constructor(private val activity: Activity) : WebViewClient() {

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        val url: String = request?.url.toString();
        view?.loadUrl(url)
        return true
    }

    override fun shouldOverrideUrlLoading(webView: WebView, url: String): Boolean {
        webView.loadUrl(url)
        return true
    }

    override fun onReceivedError(
        view: WebView,
        request: WebResourceRequest,
        error: WebResourceError
    ) {
        Toast.makeText(activity, "Got Error! $error", Toast.LENGTH_SHORT).show()
    }
}