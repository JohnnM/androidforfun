package com.johnnmancilla.androidforfun.interfaces


interface IInteractor<T> {

        fun loadData(onFinishLoadData: onFinishLoadData<T>)

        interface onFinishLoadData<T> {
            fun onSuccessLoadData(value: List<T>?)
            fun onErrorLoadData(msg: String)
        }
}