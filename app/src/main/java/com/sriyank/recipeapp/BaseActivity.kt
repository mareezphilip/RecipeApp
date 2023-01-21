package com.sriyank.recipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.sriyank.recipeapp.entities.Category
import com.sriyank.recipeapp.interfaces.GetDataService
import com.sriyank.recipeapp.retrofitclient.RetrofitClientInstance
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import retrofit2.Call

import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

open class BaseActivity : AppCompatActivity(), CoroutineScope {
    private lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()

    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }


}