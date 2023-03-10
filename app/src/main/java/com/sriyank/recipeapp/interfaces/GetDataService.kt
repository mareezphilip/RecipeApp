package com.sriyank.recipeapp.interfaces


import com.sriyank.recipeapp.entities.Category
import com.sriyank.recipeapp.entities.Meal
import com.sriyank.recipeapp.entities.MealResponse
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Query
import java.util.*

interface GetDataService {
    @GET("categories.php")
    fun getCategoryList() :Call<Category>

    @GET("filter.php")
    fun getMealList(@Query("c") category :String) :Call<Meal>

    @GET("lookup.php")
    fun getSpecificItem(@Query("i") id :String) :Call<MealResponse>
}