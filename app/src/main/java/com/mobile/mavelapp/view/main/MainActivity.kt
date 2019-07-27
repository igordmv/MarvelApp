package com.mobile.mavelapp.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mobile.mavelapp.R
import com.mobile.mavelapp.model.Result
import com.mobile.mavelapp.view.ViewInterface

class MainActivity : AppCompatActivity(),  ViewInterface {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun requestFailed() {

    }

    override fun requestSuccess(results: ArrayList<Result>) {
    }

}
