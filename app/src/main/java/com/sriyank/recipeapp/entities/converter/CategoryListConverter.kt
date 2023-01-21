package com.sriyank.recipeapp.entities.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sriyank.recipeapp.entities.Category
import com.sriyank.recipeapp.entities.Categoryitems

class CategoryListConverter {
    @TypeConverter
    fun fromCategoryList(category: MutableList<Categoryitems>):String?{
        if(category == null){
            return (null)
        }else{
            val gson = Gson()
            val type = object : TypeToken<Categoryitems>(){

            }.type
            return gson.toJson(category,type)
        }
    }

    @TypeConverter
    fun toCategoryList(categoryString:String):MutableList<Categoryitems>?{
        if(categoryString==null){
            return(null)
        }else{
            val gson = Gson()
            val type = object :TypeToken<Categoryitems>(){

            }.type
            return gson.fromJson(categoryString,type)

        }
    }

}