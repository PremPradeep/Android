package com.example.shop_1

import android.content.Context
import org.json.JSONArray
import org.json.JSONException


class Shop(
    val id: String,
    val name: String,
    val price: String,
    val image: String,
    val stock: String) {

    companion object {

        fun getProductsFromFile(filename: String, context: Context): ArrayList<Shop> {
            val shopList = ArrayList<Shop>()

            try {
                // Load data
                val json = loadJsonFromAsset(filename, context)
                var products= JSONArray(json)

                // Get Recipe objects from data
                (0 until products.length()).mapTo(shopList) {
                    Shop(products.getJSONObject(it).getString("id"),
                        products.getJSONObject(it).getString("name"),
                        products.getJSONObject(it).getString("price"),
                        products.getJSONObject(it).getString("image"),
                        products.getJSONObject(it).getString("stock"))
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            return shopList
        }

        private fun loadJsonFromAsset(filename: String, context: Context): String? {
            var json: String? = null

            try {
                val inputStream = context.assets.open(filename)
                val size = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()
                json = String(buffer, Charsets.UTF_8)
            } catch (ex: java.io.IOException) {
                ex.printStackTrace()
                return null
            }

            return json
        }
    }
}