package com.example.gravitytest.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import com.example.gravitytest.FragmentUtil
import com.example.gravitytest.R
import com.example.gravitytest.presenters.LoadPresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class LoadFragment : Fragment(), LoadView {
    private lateinit var presenter: LoadPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_load, container, false)
        view.findViewById<ImageView>(R.id.loadImage)
            .startAnimation(AnimationUtils.loadAnimation(context, R.anim.rotate_anim))
        presenter = LoadPresenter(requireContext())

        presenter.attachView(this)

        GlobalScope.launch(Dispatchers.IO){ presenter.loadNextView() }

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun loadHomeFragment(path:String){
        val fragment = HomeFragment()
        val bundle = Bundle()
        bundle.putString("path", path)
        fragment.arguments = bundle

        FragmentUtil().swapFragments(requireActivity().supportFragmentManager,
            R.id.fragmentContainer,fragment)
    }

    override fun showError(message: String) {
        Toast.makeText(requireContext(),message,Toast.LENGTH_LONG).show()
    }
}