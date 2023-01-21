package com.sriyank.recipeapp.interfaces


import com.sriyank.recipeapp.entities.Category
import retrofit2.http.GET
import retrofit2.Call
import java.util.*

interface GetDataService {
    @GET("categories.php")
    fun getCategoryList() :Call<Category>
}