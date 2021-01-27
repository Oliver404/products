package com.oliverbotello.products.service

import com.oliverbotello.products.R


abstract class DataService {
    enum class ERROR(val idMessage: Int) {
        DOWNLOAD_DATA_EXCEPTION(R.string.error_download_data)
    }

    var dataListener: IDataListener? = null

    abstract fun getProducts(search: String): Unit
}