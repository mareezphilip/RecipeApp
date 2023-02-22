package com.sriyank.recipeapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sriyank.recipeapp.R
import com.sriyank.recipeapp.entities.MealsItems
import com.sriyank.recipeapp.entities.Recipies
import kotlinx.android.synthetic.main.item_rv_main_category.view.*

class SubCategoryAdapter:RecyclerView.Adapter<SubCategoryAdapter.RecycleViewHolder>() {

    var ctx : Context?=null
    var arrsubcategory = ArrayList<MealsItems>()
    class RecycleViewHolder(view:View):RecyclerView.ViewHolder(view){

    }

    fun setData(arrData :List<MealsItems>){
        arrsubcategory = arrData as ArrayList<MealsItems>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleViewHolder {
        ctx = parent.context
        return RecycleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_rv_sub_category , parent , false))
    }

    override fun onBindViewHolder(holder: RecycleViewHolder, position: Int) {
      holder.itemView.tv_dish_name.text= arrsubcategory[position].strMeal
        Glide.with(ctx!!).load(arrsubcategory[position].strMealThumb).into(holder.itemView.img_dish)
    }

    override fun getItemCount(): Int {
         return arrsubcategory.size
    }

}