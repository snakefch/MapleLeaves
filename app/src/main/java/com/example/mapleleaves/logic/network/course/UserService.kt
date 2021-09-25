package com.example.mapleleaves.logic.network.course

import com.example.mapleleaves.logic.model.*
import retrofit2.Call
import retrofit2.http.*

interface UserService {

    @POST("login?")
    fun postLogin(@Query("username") userName:String, @Query("password") passWord:String ): Call<UserResponse>

    @GET("getCoursesAttended?")
    fun getCoursesAttended(@Query("studentId") studentId:String):Call<CoursesAttendedResponse>

    @POST("joinTheCourse?")
    fun joinTheCourse(@Query("studentId") studentId: String,@Query("addClassCode") addClassCode:String):Call<JoinTheCourseResponse>

    //创建课堂
    @POST("createCourse")
    fun createCourse(@Body course: CourseForCreate):Call<CourseForCreateResponse>
}