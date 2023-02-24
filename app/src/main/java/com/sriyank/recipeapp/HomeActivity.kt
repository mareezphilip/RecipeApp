package com.sriyank.recipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.sriyank.recipeapp.adapter.MainCategoryAdapter
import com.sriyank.recipeapp.adapter.SubCategoryAdapter
import com.sriyank.recipeapp.database.RecipeDatabase
import com.sriyank.recipeapp.entities.Category
import com.sriyank.recipeapp.entities.Categoryitems
import com.sriyank.recipeapp.entities.MealsItems
import com.sriyank.recipeapp.entities.Recipies
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.coroutines.launch

class HomeActivity : BaseActivity() {

    var arrmaincategory = ArrayList<Categoryitems>()
    var arrsubcategory= ArrayList<MealsItems>()
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
        /* arrsubcategory.add(Recipies(1 ,"beef and mustard pie" ))
        arrsubcategory.add(Recipies(2 ,"chicken and mushroom" ))
        arrsubcategory.add(Recipies(3 ,"Banana pancakes" ))
        arrsubcategory.add(Recipies(4 ,"kapsalon" ))

        subCategoryAdapter.setData(arrsubcategory)
*/
        //   rv_sub_category.layoutManager = LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL , false)
        //   rv_sub_category.adapter = subCategoryAdapter
        getDataFromDb()

        mainCategoryAdapter.setClickListener(onClicked)
        subCategoryAdapter.setClickListener(onClickedsub)

    }

    private val onClicked = object :MainCategoryAdapter.OnItemClickListener{
        override fun onClicked(categoryName: String) {
            getMealDataFromDb(categoryName)
        }
    }

    private val onClickedsub = object :SubCategoryAdapter.OnItemClickListener{
        override fun onClicked(id: String) {
            var intent = Intent(this@HomeActivity, DetailActivity::class.java)
            intent.putExtra("id" , id)
            startActivity(intent)
        }
    }

    private fun getDataFromDb(){
        launch {
            this.let {
                var cat = RecipeDatabase.getDatabase(this@HomeActivity).recipeDao().getAllCategory()
                arrmaincategory=cat as ArrayList<Categoryitems>
                arrmaincategory.reverse()
                getMealDataFromDb(arrmaincategory[0].strcategory)
                mainCategoryAdapter.setData(arrmaincategory)
                rv_main_category.layoutManager = LinearLayoutManager(this@HomeActivity , LinearLayoutManager.HORIZONTAL , false)
                rv_main_category.adapter = mainCategoryAdapter
            }


        }
    }

    private fun getMealDataFromDb(categoryName :String ){
        tvcategory.text = "$categoryName Category"
        launch {
            this.let {
                var cat = RecipeDatabase.getDatabase(this@HomeActivity).recipeDao().getSpecificMealList(categoryName)
                arrsubcategory=cat as ArrayList<MealsItems>
                subCategoryAdapter.setData(arrsubcategory)
                rv_sub_category.layoutManager = LinearLayoutManager(this@HomeActivity , LinearLayoutManager.HORIZONTAL , false)
                rv_sub_category.adapter =subCategoryAdapter
            }


        }
    }

}