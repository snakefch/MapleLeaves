package com.example.mapleleaves.logic.network.course

import android.util.Log
import com.example.mapleleaves.logic.model.CourseForCreate
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object CourseNetwork {

    private val userService=CourseServiceCreator.create(UserService::class.java)

    //用户登录
    suspend fun postLogin(userName:String,passName:String)= userService.postLogin(userName,passName).await()

    //学生事务-----------------------------------

    suspend fun getCoursesAttended(studentId:String)= userService.getCoursesAttended(studentId).await()

    suspend fun joinTheCourse(studentId:String, addClassCode:String)= userService.joinTheCourse(studentId, addClassCode).await()

    //老师事务------------------------------------

    suspend fun createCourse(courseForCreate: CourseForCreate)= userService.createCourse(courseForCreate).await()

    suspend fun getCoursesCreated(id:String)= userService.getCoursesCreated(id).await()

    private suspend fun <T> Call<T>.await():T{

        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body=response.body()
                    Log.d("body", body.toString())
                    if (body!=null) continuation.resume(body)
                    else continuation.resumeWithException(RuntimeException("response body is null"))
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })

        }

    }

}