package com.example.gravitytest

import android.app.Activity
import android.content.*
import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView

import java.util.prefs.Preferences
import kotlin.coroutines.CoroutineContext


class LoadFragment : Fragment() {
    private val APP_PREFERENCES = "myData"
    private val HOME_Path = "homePath"
    private lateinit var prefs: SharedPreferences
    private val BROADCAST_ACTION = "Path_is_there"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_load, container, false)
        view.findViewById<ImageView>(R.id.loadImage)
            .startAnimation(AnimationUtils.loadAnimation(context,R.anim.rotate_anim))

        prefs =
            requireActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

        requireActivity().registerReceiver(myBroadcastReceiver, IntentFilter(BROADCAST_ACTION))

        StartWebFragment().execute(requireActivity())

        /*if (savedInstanceState == null){
            if (isFirstTime())
                DataLoader().getLink(requireActivity(), BROADCAST_ACTION)
            else
                loadHomePath()
        }*/

        return view
    }

    private fun loadHomePath() {

        var path = prefs.getString(HOME_Path, null)
        if ( path != null){
            val intent = Intent(BROADCAST_ACTION)
            intent.putExtra("path", path)
            requireActivity().sendBroadcast(intent)
            return
        }
        DataLoader().getHome(requireActivity(), BROADCAST_ACTION)

    }

    private fun isFirstTime():Boolean {
        prefs = requireActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        return if (prefs.getBoolean("first_time", true)) {
            val editor = prefs.edit()
            editor.putBoolean("first_time", false)
            editor.apply()
            true
        } else false
    }

    private fun loadHomeFragment(path:String){
        val fragment = HomeFragment()
        val bundle = Bundle()
        bundle.putString("path", path)
        fragment.arguments = bundle

        FragmentUtil().swapFragments(requireActivity().supportFragmentManager,R.id.fragmentContainer,fragment)
        requireActivity().unregisterReceiver(myBroadcastReceiver)
    }

    private val myBroadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val path = intent.getStringExtra("path")
            if (path != null) {
                if(intent.getStringExtra("type").equals("home")){
                    val editor = prefs.edit()
                    editor.putString(HOME_Path, path)
                    editor.apply()
                }
                loadHomeFragment(path)
            }
        }
    }

    class StartWebFragment: AsyncTask<Activity, Void, Void>() {
        override fun doInBackground(vararg params: Activity?): Void? {

            if (isFirstTime(params[0]!!))
                DataLoader().getLink(params[0]!!, "Path_is_there")
            else
                loadHomePath(params[0]!!)
            return null
        }

        private fun loadHomePath(activity: Activity) {
            val prefs = activity.getSharedPreferences("myData", Context.MODE_PRIVATE)
            var path = prefs.getString("homePath", null)
            if ( path != null){
                val intent = Intent("Path_is_there")
                intent.putExtra("path", path)
                activity.sendBroadcast(intent)
                return
            }
            DataLoader().getHome(activity, "Path_is_there")

        }

        private fun isFirstTime(activity: Activity):Boolean {
            val prefs = activity.getSharedPreferences("myData", Context.MODE_PRIVATE)
            return if (prefs.getBoolean("first_time", true)) {
                val editor = prefs.edit()
                editor.putBoolean("first_time", false)
                editor.apply()
                true
            } else false
        }

    }


}