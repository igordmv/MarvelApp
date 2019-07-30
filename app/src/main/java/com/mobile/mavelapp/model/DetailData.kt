package com.mobile.mavelapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

class DetailData {
    @SerializedName("offset")
    @Expose
    var offset: Int? = null
    @SerializedName("limit")
    @Expose
    var limit: Int? = null
    @SerializedName("total")
    @Expose
    var total: Int? = null
    @SerializedName("count")
    @Expose
    var count: Int? = null
    @SerializedName("results")
    @Expose
    var results = ArrayList<DetailResult>()
}