package com.johnnmancilla.androidforfun

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.johnnmancilla.androidforfun.model.response.Laptop
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*


class DetailActivity : AppCompatActivity() {


    var itemSelected:Laptop?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val itemSelectedString = (intent?.extras?.get("itemSelected")?:"").toString()
        itemSelected= Gson().fromJson(itemSelectedString,Laptop::class.java)
        setUpView()

    }

    fun setUpView(){

        detail_title.setText(itemSelected?.title)
        detail_description.setText(itemSelected?.description)
        Picasso.get().load(itemSelected?.image).into(detail_image)

    }
}
