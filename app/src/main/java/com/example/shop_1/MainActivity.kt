package com.example.shop_1

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        listView = findViewById<ListView>(R.id.shop_list_view)

        val shopList = Shop.getProductsFromFile("products.json", this)

        val adapter = shopAdapter(this, shopList)
        listView.adapter = adapter




    }

}


