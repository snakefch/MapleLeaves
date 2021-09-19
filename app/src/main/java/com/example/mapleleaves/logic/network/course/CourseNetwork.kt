package com.example.mapleleaves.logic.network.course

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object CourseNetwork {

    private val userService=CourseServiceCreator.create(UserService::class.java)

    suspend fun postLogin(userName:String,passName:String)= userService.postLogin(userName,passName).await()

    suspend fun getCoursesAttended(studentId:String)= userService.getCoursesAttended(studentId).await()

    suspend fun joinTheCourse(studentId:String, addClassCode:String)= userService.joinTheCourse(studentId, addClassCode).await()

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