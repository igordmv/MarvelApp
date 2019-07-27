package com.mobile.mavelapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import java.util.ArrayList

class Stories {

    @SerializedName("available")
    @Expose
    var available: Int? = null
    @SerializedName("collectionURI")
    @Expose
    var collectionURI: String? = null
    @SerializedName("items")
    @Expose
    var items = ArrayList<Item>()
    @SerializedName("returned")
    @Expose
    var returned: Int? = null

}