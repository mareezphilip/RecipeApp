package com.sriyank.recipeapp.entities

import androidx.room.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.sriyank.recipeapp.entities.converter.CategoryListConverter

@Entity(tableName = "Category")
data class Category (
    @PrimaryKey(autoGenerate = true)
    var id:Int,

    @ColumnInfo(name = "categoryItems")
    @Expose
    @SerializedName("categories")
    @TypeConverters(CategoryListConverter::class)
    var categorieitems: MutableList<Categoryitems>?
)

