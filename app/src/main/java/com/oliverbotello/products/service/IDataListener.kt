package com.oliverbotello.products.service

import com.oliverbotello.products.entities.Product

interface IDataListener {
    fun onSuccessGetData(lstProducts: List<Product>)
    fun onFailedGetData(error: DataService.ERROR)
}