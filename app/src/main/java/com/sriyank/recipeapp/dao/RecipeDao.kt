package com.sriyank.recipeapp.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sriyank.recipeapp.entities.Category
import com.sriyank.recipeapp.entities.Categoryitems
import com.sriyank.recipeapp.entities.Recipies

@Dao
interface RecipeDao {

    @Query("SELECT * FROM categoryitems ORDER BY id DESC")
    suspend fun getAllCategory():List<Categoryitems>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(categoryitems: Categoryitems)

    @Query("DELETE FROM categoryitems")
    suspend fun clearDb()




}