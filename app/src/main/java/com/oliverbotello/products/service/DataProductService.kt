package com.oliverbotello.products.service

import com.oliverbotello.products.entities.ProductsResponse
import com.oliverbotello.products.utils.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DataProductService : DataService() {
    companion object {
        private var service: IDataProductAPI? = null
    }

    init {
        if (service == null) {
            val retrofit = Retrofit.Builder()
                .baseUrl(URL_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            service = retrofit.create(IDataProductAPI::class.java)
        }
    }

    override fun getProducts(search: String) {
        val callback: Callback<ProductsResponse> = object : Callback<ProductsResponse> {
            override fun onResponse(call: Call<ProductsResponse>, response: Response<ProductsResponse>) {
                if (response.isSuccessful && response.body() != null)
                    this@DataProductService.dataListener?.onSuccessGetData(response.body()!!.items)
            }

            override fun onFailure(call: Call<ProductsResponse>, t: Throwable) {
                this@DataProductService.dataListener?.onFailedGetData(ERROR.DOWNLOAD_DATA_EXCEPTION)
            }
        }

        service?.getProducts(search)?.enqueue(callback)
    }
}