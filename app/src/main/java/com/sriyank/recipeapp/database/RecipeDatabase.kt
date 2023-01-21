package com.sriyank.recipeapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sriyank.recipeapp.dao.RecipeDao
import com.sriyank.recipeapp.entities.Category
import com.sriyank.recipeapp.entities.Categoryitems
import com.sriyank.recipeapp.entities.Recipies
import com.sriyank.recipeapp.entities.converter.CategoryListConverter

@Database(entities = [Recipies::class , Categoryitems::class , Category::class , CategoryListConverter::class] , version =1 , exportSchema = false )
abstract class RecipeDatabase :RoomDatabase(){

    companion object{
        var recipesDatabase:RecipeDatabase? = null

        @Synchronized
        fun getDatabase(context :Context): RecipeDatabase{
            if(recipesDatabase == null ){
                recipesDatabase = Room.databaseBuilder(
                    context , RecipeDatabase::class.java ,
                    "recipe.db"
                ).build()
            }
            return recipesDatabase!!
        }
    }

    abstract fun recipeDao():RecipeDao
}