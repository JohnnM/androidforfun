package com.johnnmancilla.androidforfun.presenters

import android.util.Log
import com.johnnmancilla.androidforfun.interactors.MainInteractor
import com.johnnmancilla.androidforfun.interfaces.IInteractor
import com.johnnmancilla.androidforfun.model.response.Laptop
import com.johnnmancilla.androidforfun.views.MainView

class MainPresenter<T>: IInteractor.onFinishLoadData<T> {

    private var mainView: MainView? = null
    private var interactor:IInteractor<T>? = null

    constructor(mainView:MainView,interactor:MainInteractor<T>){
        this.mainView = mainView
        this.interactor = interactor
    }

    fun onResume() {
        if (mainView != null) {
            mainView?.showProgress()
        }

        interactor?.loadData(this)
    }


    override fun onSuccessLoadData(value: List<T>?) {
        if (mainView != null) {
            mainView?.setItems(value as? List<Laptop>)
            mainView?.hidePogress()
        }
        Log.d("T","ssss")
    }

    override fun onErrorLoadData(msg: String) {
        Log.d("T",msg)

    }

}