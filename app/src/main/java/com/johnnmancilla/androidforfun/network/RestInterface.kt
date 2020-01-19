package com.johnnmancilla.androidforfun.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface RestInterface {
    //interface that contains all endpoints for the api

    @GET(value = "list")
    fun getLaptops():Call<ResponseBody>

}