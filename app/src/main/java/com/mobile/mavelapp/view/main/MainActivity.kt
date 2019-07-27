package com.mobile.mavelapp.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mobile.mavelapp.R
import com.mobile.mavelapp.model.Result
import com.mobile.mavelapp.presenter.MainPresenter
import com.mobile.mavelapp.presenter.PresenterModel
import com.mobile.mavelapp.view.ViewInterface

class MainActivity : AppCompatActivity(),  ViewInterface {

    lateinit var presenterLogic :  MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenterLogic = MainPresenter(PresenterModel())
        presenterLogic.setView(this,this)
        presenterLogic.callHeroListRequest()
    }

    override fun requestFailed() {

    }

    override fun requestSuccess(results: ArrayList<Result>) {
    }

}
