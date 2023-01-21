package com.sriyank.recipeapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sriyank.recipeapp.R
import com.sriyank.recipeapp.entities.Recipies
import kotlinx.android.synthetic.main.item_rv_main_category.view.*

class SubCategoryAdapter:RecyclerView.Adapter<SubCategoryAdapter.RecycleViewHolder>() {

    var arrsubcategory = ArrayList<Recipies>()
    class RecycleViewHolder(view:View):RecyclerView.ViewHolder(view){

    }

    fun setData(arrData :List<Recipies>){
        arrsubcategory = arrData as ArrayList<Recipies>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleViewHolder {
        return RecycleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_rv_sub_category , parent , false))
    }

    override fun onBindViewHolder(holder: RecycleViewHolder, position: Int) {
      holder.itemView.tv_dish_name.text= arrsubcategory[position].dishName
    }

    override fun getItemCount(): Int {
         return arrsubcategory.size
    }

}