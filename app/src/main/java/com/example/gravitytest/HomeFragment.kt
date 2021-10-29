package com.example.gravitytest

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.net.http.SslCertificate.restoreState
import android.net.http.SslCertificate.saveState
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.OnBackPressedCallback


class HomeFragment : Fragment() {
    lateinit var webView: WebView
    private val webViewKey = "Webview"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        webView = root.findViewById(R.id.homeWebView)
        webView.settings.javaScriptEnabled = true
        class MyWebViewClient : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }
        }

        webView.webViewClient = MyWebViewClient()
        if (savedInstanceState == null){
            if (arguments != null){
                webView.loadUrl(requireArguments().get("path").toString())
            }else Toast.makeText(context, "Page path not found", Toast.LENGTH_LONG).show()
        }

        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if(webView.canGoBack()){
                        webView.goBack();
                    }
                    else{
                        requireActivity().finish()
                    }
                }
            }
            )

        return root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val bundle = Bundle()
        webView.saveState(bundle)
        outState.putBundle(webViewKey, bundle)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null) {
            savedInstanceState.getBundle(webViewKey)?.let { webView.restoreState(it) }
        }
    }


}