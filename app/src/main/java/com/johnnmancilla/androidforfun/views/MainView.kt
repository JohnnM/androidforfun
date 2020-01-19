package com.johnnmancilla.androidforfun.views

import com.johnnmancilla.androidforfun.model.response.Laptop

interface MainView {

    fun showProgress()

    fun hidePogress()

    fun setItems(items:List<Laptop>?)




}