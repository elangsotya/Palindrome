package com.example.palindromeapp.service

import com.example.palindromeapp.DataItem
import com.example.palindromeapp.UserResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("users?page=1&per_page=10")
    fun getUserList(): Call<UserResponse>
}