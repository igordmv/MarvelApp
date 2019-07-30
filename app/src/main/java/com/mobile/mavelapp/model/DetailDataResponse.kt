package com.mobile.mavelapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class DetailDataResponse {
    @SerializedName("code")
    @Expose
    var code: Int? = null
    @SerializedName("status")
    @Expose
    var status: String? = null
    @SerializedName("copyright")
    @Expose
    var copyright: String? = null
    @SerializedName("attributionText")
    @Expose
    var attributionText: String? = null
    @SerializedName("attributionHTML")
    @Expose
    var attributionHTML: String? = null
    @SerializedName("etag")
    @Expose
    var etag: String? = null
    @SerializedName("data")
    @Expose
    var data: DetailData? = null
}