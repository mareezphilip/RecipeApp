package com.sriyank.recipeapp.entities.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sriyank.recipeapp.entities.Category
import com.sriyank.recipeapp.entities.Categoryitems
import com.sriyank.recipeapp.entities.MealsItems

class MealListConverter {
    @TypeConverter
    fun fromCategoryList(category: MutableList<MealsItems>):String?{
        if(category == null){
            return (null)
        }else{
            val gson = Gson()
            val type = object : TypeToken<MealsItems>(){

            }.type
            return gson.toJson(category,type)
        }
    }

    @TypeConverter
    fun toCategoryList(categoryString:String):MutableList<MealsItems>?{
        if(categoryString==null){
            return(null)
        }else{
            val gson = Gson()
            val type = object :TypeToken<MealsItems>(){

            }.type
            return gson.fromJson(categoryString,type)

        }
    }

}