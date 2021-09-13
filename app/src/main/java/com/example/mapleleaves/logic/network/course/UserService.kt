package com.example.mapleleaves.logic.network.course

import com.example.mapleleaves.logic.model.UserResponse
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {

    @POST("login?")
    fun postLogin(@Query("username") userName:String, @Query("password") passWord:String ): Call<UserResponse>


}