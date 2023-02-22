package com.sriyank.recipeapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sriyank.recipeapp.dao.RecipeDao
import com.sriyank.recipeapp.entities.*
import com.sriyank.recipeapp.entities.converter.CategoryListConverter
import com.sriyank.recipeapp.entities.converter.MealListConverter

@Database(entities = [Recipies::class , Categoryitems::class , Category::class , Meal::class , MealsItems::class] , version =1 , exportSchema = false )
@TypeConverters(CategoryListConverter::class, MealListConverter::class)
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