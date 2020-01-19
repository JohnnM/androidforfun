package com.johnnmancilla.androidforfun.interactors

import android.util.Log
import com.google.gson.Gson
import com.johnnmancilla.androidforfun.interfaces.IInteractor
import com.johnnmancilla.androidforfun.model.response.Laptop
import com.johnnmancilla.androidforfun.network.RestInterface
import com.johnnmancilla.androidforfun.network.WebNetwork
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.google.gson.reflect.TypeToken



class MainInteractor<T>:IInteractor<T> {

    private val type = "MAIN INTERACTOR"
    private var call: Call<ResponseBody>? = null



    override fun loadData(onFinishLoadData: IInteractor.onFinishLoadData<T>) {

        val webNetwork = WebNetwork("token")
        val apiClientNetwork: RestInterface?

        apiClientNetwork = webNetwork.createEndPoint()

        call = apiClientNetwork?.getLaptops()
        Log.i("UNO","test")



        call?.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    try {
                        val listType = object : TypeToken<List<Laptop>>() {}.type
                        val responseParsed:List<Laptop> = Gson().fromJson(response.body()?.string(),listType)


                        if(responseParsed.isEmpty()){
                            onFinishLoadData.onErrorLoadData(msg="No se encuentran registros.")
                        }else {
                            onFinishLoadData.onSuccessLoadData(
                                responseParsed as? List<T>
                            )
                        }
                    } catch (e: Exception) {
                        onFinishLoadData.onErrorLoadData("Lo sentimos. Ha ocurrido un error ")
                        return
                    }

                } else if (response.code() == 401) {
                    Log.d(type, "Error user unauthorized 401")
                    onFinishLoadData.onErrorLoadData("Error usuario no autenticado")
                } else if (response.code() == 404) {
                    onFinishLoadData.onErrorLoadData("Error conectando con los servicios.")
                } else {
                    Log.d(type, "Error on receive data")
                    onFinishLoadData.onErrorLoadData("No se han encontrado datos.")
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d(type, "Fail to communicate")
                if (call.isCanceled()) {
                    Log.e("callCanceled", "request was cancelled")
                } else
                    onFinishLoadData.onErrorLoadData("Error obteniendo los datos")
            }
        })
    }






}