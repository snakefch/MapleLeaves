package com.example.mapleleaves.logic.network.course

import com.example.mapleleaves.logic.model.CoursesAttendedResponse
import com.example.mapleleaves.logic.model.JoinTheCourseResponse
import com.example.mapleleaves.logic.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {

    @POST("login?")
    fun postLogin(@Query("username") userName:String, @Query("password") passWord:String ): Call<UserResponse>

    @GET("getCoursesAttended?")
    fun getCoursesAttended(@Query("studentId") studentId:String):Call<CoursesAttendedResponse>

    @POST("joinTheCourse?")
    fun joinTheCourse(@Query("studentId") studentId: String,@Query("addClassCode") addClassCode:String):Call<JoinTheCourseResponse>

}