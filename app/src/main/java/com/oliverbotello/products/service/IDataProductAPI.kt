package com.oliverbotello.products.service

import com.oliverbotello.products.entities.ProductsResponse
import com.oliverbotello.products.utils.HEADER_VALUE
import retrofit2.Call
import retrofit2.http.*

interface IDataProductAPI {
    @Headers(HEADER_VALUE)
    @GET("demo-gapsi/search")
    fun getProducts(@Query("query") search: String): Call<ProductsResponse>
}