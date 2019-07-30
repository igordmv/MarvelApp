package com.mobile.mavelapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

class DetailResult {
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("thumbnail")
    @Expose
    var thumbnail: Thumbnail? = null
    @SerializedName("resourceURI")
    @Expose
    val resourceURI: String? = null
    @SerializedName("urls")
    @Expose
    private val urls = ArrayList<Url>()

    override fun equals(object2: Any?): Boolean {
        return object2 is Result && id == object2.id
    }
}