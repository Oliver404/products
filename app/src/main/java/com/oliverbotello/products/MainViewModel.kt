package com.oliverbotello.products

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oliverbotello.products.database.DBManager
import com.oliverbotello.products.database.sqlite.SQLiteManager
import com.oliverbotello.products.entities.Product
import com.oliverbotello.products.entities.Search
import com.oliverbotello.products.service.DataProductService
import com.oliverbotello.products.service.DataService
import com.oliverbotello.products.service.IDataListener
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainViewModel(application: Application) : AndroidViewModel(application), IDataListener {
    var listener: IMain? = null
    val search: MutableLiveData<String> = MutableLiveData()
    val lstProducts: MutableLiveData<List<Product>> = MutableLiveData(listOf())
    val lstSearch: MutableLiveData<List<Search>> = MutableLiveData(listOf())
    private val service: DataService = DataProductService()
    private val db: DBManager = SQLiteManager(application.applicationContext)

    init {
        this.service.dataListener = this
    }

    fun searching(value: String) {
        if (value.isNullOrBlank()) {
            this@MainViewModel.lstSearch.value = listOf()

            return
        }

        doAsync {
            val result = db.getSearchs(value)

            uiThread {
                this@MainViewModel.lstSearch.value = result
            }
        }
    }

    fun search(search: String) {
        this.lstSearch.value = listOf()

        this.listener?.onStartSearch()
        this.service.getProducts(search)
        doAsync {
            try {
                this@MainViewModel.db.insertSearch(Search(search))
            } catch (e: Exception) {}
        }
    }

    override fun onSuccessGetData(lstProducts: List<Product>) {
        this.lstProducts.value = lstProducts
        this.listener?.onSuccessSearch()
    }

    override fun onFailedGetData(error: DataService.ERROR) {
        this.listener?.onFailedSearch()
    }
}