package com.example.gravitytest.presenters

import android.content.Context
import com.example.gravitytest.views.LoadView
import com.example.gravitytest.models.Preferences
import com.example.gravitytest.models.ServerModel
import com.example.gravitytest.useCases.DataLoadUseCase

class LoadPresenter(context: Context) {
    private val preferences = Preferences(context)
    private var view: LoadView? = null

    fun attachView(newView: LoadView){
        view = newView
    }

    fun detachView(){
        view = null
    }

    fun loadNextView(){
        if (DataLoadUseCase().isFirstTime(preferences)){
            //get link and load it
            ServerModel().getLink()?.let { view?.loadHomeFragment(it)}
        }else{
            getHomePath()
        }
    }

    private fun getHomePath(){
        var path = preferences.getHomePath()
        if (path== null){
            path = ServerModel().getHome()
            if (path != null){
                preferences.editHomePath(path)
            }else{
                //say that server didn`t available
                view?.showError("Server don`t respond")
                return
            }
        }
        //load page
        view?.loadHomeFragment(path)
    }
}