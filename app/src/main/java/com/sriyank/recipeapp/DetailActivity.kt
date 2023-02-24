package com.sriyank.recipeapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.sriyank.recipeapp.adapter.MainCategoryAdapter
import com.sriyank.recipeapp.adapter.SubCategoryAdapter
import com.sriyank.recipeapp.database.RecipeDatabase
import com.sriyank.recipeapp.entities.*
import com.sriyank.recipeapp.interfaces.GetDataService
import com.sriyank.recipeapp.retrofitclient.RetrofitClientInstance
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home.tvcategory
import kotlinx.android.synthetic.main.item_rv_main_category.view.*
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class DetailActivity : BaseActivity() {

    var youtubeLink = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

       var  id = intent.getStringExtra("id");

        getSpecificItem(id!!)

        imgToolBarBtnBack.setOnClickListener{
            finish()
        }
        btn_youtube.setOnClickListener{
            val uri :Uri = Uri.parse(youtubeLink)

            val intent = Intent(Intent.ACTION_VIEW , uri)
            startActivity(intent)
        }

    }


    fun getSpecificItem(id :String){
        val service = RetrofitClientInstance.retrofitInstance!!.create(GetDataService::class.java)
        val call = service.getSpecificItem(id)



        call.enqueue(object : retrofit2.Callback<MealResponse> {
            override fun onResponse(call: Call<MealResponse>, response: Response<MealResponse>) {
               if(response.body()!!.meals.isEmpty()){
                   Toast.makeText(this@DetailActivity , "Detail not found" , Toast.LENGTH_SHORT).show()
               }else{
                   Glide.with(this@DetailActivity).load(response.body()!!.meals[0].strMealThumb).into(imgItem)
                   tvcategory.text=response.body()!!.meals[0].strMeal
                   var ingredient = "${response.body()!!.meals[0].strIngredient1}      ${response.body()!!.meals[0].strMeasure1}\n" +
                           "${response.body()!!.meals[0].strIngredient2}      ${response.body()!!.meals[0].strMeasure2}\n" +
                           "${response.body()!!.meals[0].strIngredient3}      ${response.body()!!.meals[0].strMeasure3}\n" +
                           "${response.body()!!.meals[0].strIngredient4}      ${response.body()!!.meals[0].strMeasure4}\n" +
                           "${response.body()!!.meals[0].strIngredient5}      ${response.body()!!.meals[0].strMeasure5}\n" +
                           "${response.body()!!.meals[0].strIngredient6}      ${response.body()!!.meals[0].strMeasure6}\n" +
                           "${response.body()!!.meals[0].strIngredient7}      ${response.body()!!.meals[0].strMeasure7}\n" +
                           "${response.body()!!.meals[0].strIngredient8}      ${response.body()!!.meals[0].strMeasure8}\n" +
                           "${response.body()!!.meals[0].strIngredient9}      ${response.body()!!.meals[0].strMeasure9}\n" +
                           "${response.body()!!.meals[0].strIngredient10}      ${response.body()!!.meals[0].strMeasure10}\n" +
                           "${response.body()!!.meals[0].strIngredient11}      ${response.body()!!.meals[0].strMeasure11}\n" +
                           "${response.body()!!.meals[0].strIngredient12}      ${response.body()!!.meals[0].strMeasure12}\n" +
                           "${response.body()!!.meals[0].strIngredient13}      ${response.body()!!.meals[0].strMeasure13}\n" +
                           "${response.body()!!.meals[0].strIngredient14}      ${response.body()!!.meals[0].strMeasure14}\n" +
                           "${response.body()!!.meals[0].strIngredient15}      ${response.body()!!.meals[0].strMeasure15} "

                   tv_ingredients.text=ingredient
                   tv_instructions.text=response.body()!!.meals[0].strInstructions

                   if(response.body()!!.meals[0].strYoutube != null){
                       youtubeLink =response.body()!!.meals[0].strYoutube
                   }else{
                       btn_youtube.visibility=View.GONE
                   }
               }

            }

            override fun onFailure(call: Call<MealResponse>, t: Throwable) {

                Toast.makeText(this@DetailActivity, "Something went wrong", Toast.LENGTH_SHORT)
                    .show()
            }

        })
    }




}