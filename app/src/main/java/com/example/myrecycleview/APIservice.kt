package com.example.myrecycleview

import retrofit2.Call
import retrofit2.http.GET

interface APIservice {

    @GET("users")
    fun getUsers(): Call<ArrayList<UserModel>>
}