package com.example.veloz_foodecom.data.api

import com.example.veloz_foodecom.data.models.Product
import com.example.veloz_foodecom.data.models.Rating
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL

class FakeStoreApiService {

    private val baseUrl = "https://fakestoreapi.com"

    suspend fun fetchProducts(): List<Product> = withContext(Dispatchers.IO) {
        val url = URL("$baseUrl/products")
        val connection = url.openConnection() as HttpURLConnection

        try {
            connection.connectTimeout = 10000
            connection.readTimeout = 10000
            connection.requestMethod = "GET"

            val responseCode = connection.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                val response = connection.inputStream.bufferedReader().use { it.readText() }
                parseProductsResponse(response)
            } else {
                emptyList()
            }
        } finally {
            connection.disconnect()
        }
    }

    suspend fun fetchCategories(): List<String> = withContext(Dispatchers.IO){
        val url = URL("$baseUrl/products/categories")
        val connection = url.openConnection() as HttpURLConnection
        try{
            connection.connectTimeout = 10000
            connection.readTimeout = 10000
            connection.requestMethod = "GET"

            val responseCode = connection.responseCode
            if(responseCode == HttpURLConnection.HTTP_OK){
                val response = connection.inputStream.bufferedReader().use { it.readText() }
                parseCategoriesResponse(response)
            }else{
                emptyList()
            }
        }finally {
            connection.disconnect()
        }
    }

    suspend fun fetchProductsByCategory(category: String): List<Product> = withContext(Dispatchers.IO) {
        val url = URL("$baseUrl/products/category/$category")
        val connection = url.openConnection() as HttpURLConnection
        try {
            connection.connectTimeout = 10000
            connection.readTimeout = 10000
            connection.requestMethod = "GET"

            val responseCode = connection.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                val response = connection.inputStream.bufferedReader().use { it.readText() }
                parseProductsResponse(response)
            } else {
                emptyList()
            }
        } finally {
            connection.disconnect()
        }
    }


    private fun parseCategoriesResponse(response: String): List<String> {
        val categories = mutableListOf<String>()
        val jsonArray = JSONArray(response)
        for(i in 0 until jsonArray.length()){
            categories.add(jsonArray.getString(i))
        }
        return categories
    }

    // This is a placeholder – you should define how the JSON response gets converted into a list of Product
    private fun parseProductsResponse(response: String): List<Product> {
        val products = mutableListOf<Product>()
        val jsonArray = JSONArray(response)
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val id = jsonObject.getInt("id")
            val title = jsonObject.getString("title")
            val price = jsonObject.getDouble("price")
            val imageUrl = jsonObject.getString("image")
            val category = jsonObject.getString("category")
            val description = jsonObject.getString("description")
            val rating = jsonObject.getJSONObject("rating")
            val rate = rating.getDouble("rate")
            val count = rating.getInt("count")

            products.add(
                Product(
                    id = id,
                    name = title,
                    price = price,
                    imageUrl = imageUrl,
                    category = category,
                    description = description,
                    rating = Rating(
                        rate = rate,
                        count = count
                    )
                )
            )
        }
        return products
    }
}
