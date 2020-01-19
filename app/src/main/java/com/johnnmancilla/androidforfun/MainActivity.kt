package com.johnnmancilla.androidforfun

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import com.johnnmancilla.androidforfun.model.response.Laptop
import com.johnnmancilla.androidforfun.views.MainView
import androidx.recyclerview.widget.RecyclerView;
import com.johnnmancilla.androidforfun.adapters.MainAdapter
import com.johnnmancilla.androidforfun.interactors.MainInteractor
import com.johnnmancilla.androidforfun.presenters.MainPresenter
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.gson.Gson


class MainActivity<T> : AppCompatActivity(), MainView {



    private var recyclerView:RecyclerView? = null
    private var progressBar:ProgressBar? = null
    private var presenter:MainPresenter<T>?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.list)
        progressBar = findViewById(R.id.progress)
        presenter = MainPresenter(this, MainInteractor())


    }

    override fun onResume() {
        super.onResume()
        presenter?.onResume()
    }

    override fun setItems(items: List<Laptop>?) {
        recyclerView?.addItemDecoration(
            DividerItemDecoration(
                recyclerView?.context,
                DividerItemDecoration.VERTICAL
            )
        )
        recyclerView?.setLayoutManager(LinearLayoutManager(this))
        val context = this
        recyclerView?.setAdapter(object:MainAdapter(items!!){
            override fun itemSelected(itemSelected: Laptop) {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("itemSelected", Gson().toJson(itemSelected))
                startActivity(intent)
            }
        })
    }

    override fun hidePogress() {
        progressBar?.setVisibility(View.INVISIBLE)
        recyclerView?.setVisibility(View.VISIBLE)
    }

    override fun showProgress() {
        progressBar?.setVisibility(View.VISIBLE)
        recyclerView?.setVisibility(View.INVISIBLE)
    }
}
