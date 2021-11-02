package com.example.gravitytest.views

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import com.example.gravitytest.R


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