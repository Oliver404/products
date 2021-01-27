package com.oliverbotello.products

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.oliverbotello.products.adapters.product.ProductAdapter
import com.oliverbotello.products.adapters.search.SearchAdapter
import com.oliverbotello.products.databinding.ActivityMainBinding
import com.oliverbotello.products.entities.Product
import com.oliverbotello.products.entities.Search

class MainActivity : AppCompatActivity(), IMain {
    private lateinit var binding: ActivityMainBinding
    private var byClick: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMainBinding.inflate(this.layoutInflater)
        val productAdapter: ProductAdapter =
            ProductAdapter { product -> this.onClickItem(product) }
        val searchAdapter: SearchAdapter = 
            SearchAdapter { search -> this.onClickSearch(search) }
        val viewModel: MainViewModel =
            ViewModelProvider(this).get(MainViewModel::class.java)
        this.binding.rcclrVwProducts.adapter = productAdapter
        this.binding.rcclrVwSearchBefore.adapter = searchAdapter
        viewModel.listener = this
        this.binding.viewModel = viewModel

        viewModel.lstProducts.observe(
            this,
            {
                it?.let {
                    this.binding.txtVwResult.text =
                        this.getString(R.string.label_number_count, it.size)

                    productAdapter.submitList(it)
                }
            }
        )
        viewModel.lstSearch.observe(
            this,
            {
                it?.let {
                    searchAdapter.submitList(it)
                }
            }
        )


        this.binding.srchVwProducts.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.search(query?:"")

                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (!this@MainActivity.byClick)
                    viewModel.searching(query?:"")
                else
                    this@MainActivity.byClick = false

                return false
            }
        })

        this.setContentView(this.binding.root)
    }

    private fun onClickItem(product: Product) {
        Toast.makeText(this, product.title, Toast.LENGTH_LONG).show()
    }

    private fun onClickSearch(search: Search) {
        this.byClick = true

        this.binding.srchVwProducts.setQuery(search.value, true)
    }
    
    override fun onStartSearch() {
        this.binding.prgrssBrProducts.visibility = View.VISIBLE
        this.binding.rcclrVwProducts.visibility = View.GONE
    }

    override fun onSuccessSearch() {
        this.binding.prgrssBrProducts.visibility = View.GONE
        this.binding.rcclrVwProducts.visibility = View.VISIBLE
    }

    override fun onFailedSearch() {
        this.binding.prgrssBrProducts.visibility = View.GONE
        this.binding.rcclrVwProducts.visibility = View.VISIBLE
        Toast.makeText(this.applicationContext, "Surgio un error, intentelo de nuevo", Toast.LENGTH_LONG).show()
    }

}