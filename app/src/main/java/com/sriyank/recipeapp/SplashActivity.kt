package com.sriyank.recipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.sriyank.recipeapp.database.RecipeDatabase
import com.sriyank.recipeapp.entities.Category
import com.sriyank.recipeapp.entities.Meal
import com.sriyank.recipeapp.entities.MealsItems
import com.sriyank.recipeapp.interfaces.GetDataService
import com.sriyank.recipeapp.retrofitclient.RetrofitClientInstance
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import retrofit2.Call

import retrofit2.Callback
import retrofit2.Response
import java.util.jar.Manifest

class SplashActivity : BaseActivity() , EasyPermissions.RationaleCallbacks , EasyPermissions.PermissionCallbacks {
    private var READ_STORAGE_PERM = 123
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        readStorageTask()
        val btn = findViewById<Button>(R.id.btnGetStarted)
        btn.setOnClickListener{
            var intent = Intent(this@SplashActivity , HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    fun getCategories(){
        val service =RetrofitClientInstance.retrofitInstance!!.create(GetDataService::class.java)
        val call = service.getCategoryList()
        call.enqueue(object :Callback<Category>{
            override fun onResponse(call: Call<Category>, response: Response<Category>) {
                Log.i("info" , "donee")
                for (arr in response.body()!!.categorieitems!!){
                    getMeal(arr.strcategory)
                }
                insertDataIntoRoomDb(response.body())

            }

            override fun onFailure(call: Call<Category>, t: Throwable) {

                Toast.makeText(this@SplashActivity, "Something went wrong", Toast.LENGTH_SHORT)
                    .show()
            }

        })



    }


    fun getMeal(categoryName:String){
        val service =RetrofitClientInstance.retrofitInstance!!.create(GetDataService::class.java)
        val call = service.getMealList(categoryName)
        call.enqueue(object :Callback<Meal>{
            override fun onResponse(call: Call<Meal>, response: Response<Meal>) {
                Log.i("info" , "doneee")
                insertMealDataIntoRoomDb(categoryName ,response.body())

            }

            override fun onFailure(call: Call<Meal>, t: Throwable) {
                loader.visibility=View.INVISIBLE
                Log.i("info" , "fail")
                Toast.makeText(this@SplashActivity, "Something went wrong", Toast.LENGTH_SHORT)
                    .show()
            }

        })



    }

    fun insertDataIntoRoomDb( category :Category?){
         launch {
             this.let{
               //  RecipeDatabase.getDatabase(this@SplashActivity).recipeDao().clearDb()
                 for(arr in category!!.categorieitems!!){
                     RecipeDatabase.getDatabase(this@SplashActivity).recipeDao().insertCategory(arr)
                 }

             }

         }

    }

    fun insertMealDataIntoRoomDb(categoryName: String,meal :Meal?){
        var x = 5
        launch {
            this.let{
               // RecipeDatabase.getDatabase(this@SplashActivity).recipeDao().clearDb()
                var mealItemModel =MealsItems(0,"555",categoryName, "hhhhh", "https://www.themealdb.com/images/media/meals/yyrrxr1511816289.jpg")
                for(arr in meal!!.mealsItem!!){
                    if(arr.strMealThumb == null){
                        Log.i("info" , "thumb is null")
                        mealItemModel = MealsItems(
                            arr.id,arr.idMeal,categoryName,arr.strMeal,"https://www.themealdb.com/images/media/meals/vvstvq1487342592.jpg")
                    }
                    else {
                       mealItemModel = MealsItems(
                            arr.id, arr.idMeal, categoryName, arr.strMeal, arr.strMealThumb
                        )
                    }
                    RecipeDatabase.getDatabase(this@SplashActivity).recipeDao().insertMeal(mealItemModel)
                    Log.i("info" , arr.toString())
                }
                btnGetStarted.visibility = View.VISIBLE
            }

        }

    }

    fun clearDatabase(){

            launch {
                this.let{
                     RecipeDatabase.getDatabase(this@SplashActivity).recipeDao().clearDb()

                }

            }
    }


    private fun hasReadStoragePermission():Boolean{
        return EasyPermissions.hasPermissions(this , android.Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    private fun readStorageTask(){
        if(hasReadStoragePermission()){
            clearDatabase()
            getCategories()
        }else{
            EasyPermissions.requestPermissions(this , "this app needs ess to your storage, " ,  READ_STORAGE_PERM , android.Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults ,theme)
    }


    override fun onRationaleAccepted(requestCode: Int) {
        TODO("Not yet implemented")
    }

    override fun onRationaleDenied(requestCode: Int) {

    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        TODO("Not yet implemented")
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if(EasyPermissions.somePermissionPermanentlyDenied(this ,perms )){
            AppSettingsDialog.Builder(this ).build().show()
        }
    }


}