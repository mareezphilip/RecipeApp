package com.sriyank.recipeapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sriyank.recipeapp.R
import com.sriyank.recipeapp.entities.Categoryitems
import com.sriyank.recipeapp.entities.Recipies
import kotlinx.android.synthetic.main.item_rv_main_category.view.*

class MainCategoryAdapter:RecyclerView.Adapter<MainCategoryAdapter.RecycleViewHolder>() {

    var listener:OnItemClickListener? = null
    var ctx :Context? =null
    var arrmaincategory = ArrayList<Categoryitems>()
    class RecycleViewHolder(view:View):RecyclerView.ViewHolder(view){

    }

    fun setData(arrData :List<Categoryitems>){
        arrmaincategory = arrData as ArrayList<Categoryitems>
    }

    fun setClickListener(listener1 :OnItemClickListener){
        listener = listener1
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleViewHolder {
        ctx = parent.context
        return RecycleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_rv_main_category , parent , false))
    }

    override fun onBindViewHolder(holder: RecycleViewHolder, position: Int) {
      holder.itemView.tv_dish_name.text= arrmaincategory[position].strcategory

      Glide.with(ctx!!).load(arrmaincategory[position].strcategoryThumb).into(holder.itemView.img_dish)
      holder.itemView.rootView.setOnClickListener{
          listener!!.onClicked(arrmaincategory[position].strcategory)
      }

    }

    override fun getItemCount(): Int {
         return arrmaincategory.size
    }

    interface OnItemClickListener{
        fun onClicked(categoryName: String)
    }

}