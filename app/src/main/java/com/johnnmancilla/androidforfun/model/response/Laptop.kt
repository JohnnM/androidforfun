package com.johnnmancilla.androidforfun.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Laptop {

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("image")
    @Expose
    var image: String? = null

}