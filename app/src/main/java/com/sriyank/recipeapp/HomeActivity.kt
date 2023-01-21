package com.sriyank.recipeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.sriyank.recipeapp.adapter.MainCategoryAdapter
import com.sriyank.recipeapp.adapter.SubCategoryAdapter
import com.sriyank.recipeapp.database.RecipeDatabase
import com.sriyank.recipeapp.entities.Category
import com.sriyank.recipeapp.entities.Categoryitems
import com.sriyank.recipeapp.entities.Recipies
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.coroutines.launch

class HomeActivity : BaseActivity() {

    var arrmaincategory = ArrayList<Categoryitems>()
    var arrsubcategory= ArrayList<Recipies>()
    var mainCategoryAdapter = MainCategoryAdapter()
    var subCategoryAdapter = SubCategoryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

       /* arrmaincategory.add(Recipies(1 ,"beef" ))
        arrmaincategory.add(Recipies(2 ,"chicken" ))
        arrmaincategory.add(Recipies(3 ,"Dessert" ))
        arrmaincategory.add(Recipies(4 ,"Lamb" ))

        mainCategoryAdapter.setData(arrmaincategory)
*/

        getDataFromDb()
        arrsubcategory.add(Recipies(1 ,"beef and mustard pie" ))
        arrsubcategory.add(Recipies(2 ,"chicken and mushroom" ))
        arrsubcategory.add(Recipies(3 ,"Banana pancakes" ))
        arrsubcategory.add(Recipies(4 ,"kapsalon" ))

        subCategoryAdapter.setData(arrsubcategory)




        rv_sub_category.layoutManager = LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL , false)
        rv_sub_category.adapter = subCategoryAdapter
    }

    private fun getDataFromDb(){
        launch {
            this.let {
                var cat = RecipeDatabase.getDatabase(this@HomeActivity).recipeDao().getAllCategory()
                arrmaincategory=cat as ArrayList<Categoryitems>
                arrmaincategory.reverse()
                mainCategoryAdapter.setData(arrmaincategory)
                rv_main_category.layoutManager = LinearLayoutManager(this@HomeActivity , LinearLayoutManager.HORIZONTAL , false)
                rv_main_category.adapter = mainCategoryAdapter
            }


        }
    }

}