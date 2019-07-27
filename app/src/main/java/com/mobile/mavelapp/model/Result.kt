package com.mobile.mavelapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import java.util.ArrayList

class Result {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("modified")
    @Expose
    var modified: String? = null
    @SerializedName("thumbnail")
    @Expose
    var thumbnail: Thumbnail? = null
    @SerializedName("resourceURI")
    @Expose
    val resourceURI: String? = null
    @SerializedName("comics")
    @Expose
    val comics: Comics? = null
    @SerializedName("series")
    @Expose
    val series: Series? = null
    @SerializedName("stories")
    @Expose
    val stories: Stories? = null
    @SerializedName("events")
    @Expose
    val events: Events? = null
    @SerializedName("urls")
    @Expose
    private val urls = ArrayList<Url>()

    override fun equals(object2: Any?): Boolean {
        return object2 is Result && id == object2.id
    }
}
