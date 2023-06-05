package com.example.myrecycleview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myrecycleview.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RecycleViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = RecycleViewAdapter(this@MainActivity, arrayListOf())

        binding.rv.adapter = adapter
        binding.rv.setHasFixedSize(true)

        remoteGetUsers()

    }

    fun remoteGetUsers() {
        APIclient.ApiService.getUsers().enqueue(object: Callback<ArrayList<UserModel>>{
            override fun onResponse(
                call: Call<ArrayList<UserModel>>,
                response: Response<ArrayList<UserModel>>
            ) {
                if(response.isSuccessful) {
                    val data = response.body()
                    setDataToAdapater(data!!)
                }
            }

            override fun onFailure(call: Call<ArrayList<UserModel>>, t: Throwable) {
                Log.d("Error", "" + t.stackTraceToString())
            }
        })
    }

    fun setDataToAdapater(data: ArrayList<UserModel>) {
        adapter.setData(data)
    }
}