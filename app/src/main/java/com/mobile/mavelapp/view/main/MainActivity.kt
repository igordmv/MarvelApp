package com.mobile.mavelapp.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.mobile.mavelapp.R
import com.mobile.mavelapp.injection.presenterModelResolver
import com.mobile.mavelapp.model.Result
import com.mobile.mavelapp.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ViewInterface {

    lateinit var presenterLogic :  MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenterLogic = MainPresenter(presenterModelResolver())
        presenterLogic.setView(this,this)
        presenterLogic.callHeroListRequest()
    }

    override fun requestFailed() {

    }

    override fun requestSuccess(results: ArrayList<Result>) {
        dimissProgressBar()
        results.forEach(){
            Log.e("IGOR",it.name + " " + it.id)
        }
    }

    fun dimissProgressBar() {
        pbLoading.visibility = View.GONE
    }

}
