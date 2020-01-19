package com.johnnmancilla.androidforfun.network


import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.internal.bind.DateTypeAdapter

import java.util.Date
import java.util.concurrent.TimeUnit


import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



class WebNetwork(private var Accesstoken: String) {

    /*
    This class manages the resources to consume web services, also enables the corresponding headers
     */
    private var webServiceEndpoint: RestInterface? = null
    private var webServiceEndpointAuth:RestInterface?=null
    private var timeoutForRequest:Long=2

    val requestInterceptor: OkHttpClient
        get() {

            val logging = HttpLoggingInterceptor()
            val httpClient = OkHttpClient.Builder()
            httpClient.readTimeout(timeoutForRequest, TimeUnit.MINUTES)
            httpClient.connectTimeout(timeoutForRequest, TimeUnit.MINUTES)

           // if(BuildConfig.DEBUG) //print the logs in debug mode
                logging.level = HttpLoggingInterceptor.Level.BODY
            //else
              //  logging.level = HttpLoggingInterceptor.Level.NONE

            httpClient.addInterceptor { chain ->
                val original = chain.request()
                val request: Request
                request = original.newBuilder()
                    .addHeader("Content-type", "application/json")
                    .addHeader("accept", "application/json")
                    .addHeader("Authorization", Accesstoken)
                    .method(original.method(), original.body())
                    .build()
                chain.proceed(request)
            }

            httpClient.addInterceptor(logging)
            return httpClient.build()

        }

    private val gsonConverter: Gson
        get() = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .registerTypeAdapter(Date::class.java, DateTypeAdapter())
            .disableHtmlEscaping()
            //  .serializeNulls() //to add null estates
            .create()


    fun createEndPoint(): RestInterface? {

        if (webServiceEndpoint == null) {

            val client = requestInterceptor
            val retrofit = Retrofit.Builder()
                .baseUrl(WEB_SERVICE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gsonConverter))
                .client(client)
                .build()

            webServiceEndpoint = retrofit.create(RestInterface::class.java)
        }

        return webServiceEndpoint
    }


    companion object {
        private const val WEB_SERVICE_BASE_URL = "http://private-f0eea-mobilegllatam.apiary-mock.com/"
//        private const val WEB_SERVICE_BASE_URL = BuildConfig.NOTIFICATIONS_ENDPOINT
    }
}
