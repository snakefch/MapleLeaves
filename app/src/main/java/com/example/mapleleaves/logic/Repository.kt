package com.example.mapleleaves.logic

import android.util.Log
import androidx.lifecycle.liveData
import com.example.mapleleaves.logic.dao.PlaceDao
import com.example.mapleleaves.logic.dao.UserDao
import com.example.mapleleaves.logic.model.body.CreateCourseBody
import com.example.mapleleaves.logic.model.response.Place
import com.example.mapleleaves.logic.model.User
import com.example.mapleleaves.logic.model.Weather
import com.example.mapleleaves.logic.model.body.RegisterBody
import com.example.mapleleaves.logic.model.body.SignInByStudentBody
import com.example.mapleleaves.logic.model.body.StartSignInBody
import com.example.mapleleaves.logic.network.sunnyweather.SunnyWeatherNetwork
import com.example.mapleleaves.logic.network.course.CourseNetwork
import com.example.mapleleaves.utils.LogUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlin.Exception
import kotlin.coroutines.CoroutineContext

object Repository {

    fun searchPlaces(query:String) = fire(Dispatchers.IO){
               val placeResponse= SunnyWeatherNetwork.searchPlaces(query)
               if (placeResponse.status=="ok"){
                   val places=placeResponse.places
                   Result.success(places)
               }else{
                   Result.failure(RuntimeException("response status is ${placeResponse.status}"))
               }

    }

    fun refreshWeather(lng:String,lat:String)= fire(Dispatchers.IO){
        Log.d("refreshWeather","lng is $lng,lat is $lat")
            coroutineScope {
                val deferredRealtime=async {
                    SunnyWeatherNetwork.getRealtimeWeather(lng, lat)
                }
                val deferredDaily=async {
                    SunnyWeatherNetwork.getDailyWeather(lng, lat)
                }
                val realtimeResponse=deferredRealtime.await()
                val dailyResponse=deferredDaily.await()
                if (realtimeResponse.status=="ok"&&dailyResponse.status=="ok"){
                    val weather= Weather(realtimeResponse.result.realtime,dailyResponse.result.daily)
                    Result.success(weather)
                }else{
                    Result.failure(
                        RuntimeException(
                            "realtime response status is ${realtimeResponse.status}"+
                                    "daily response status is ${dailyResponse.status}"
                        ))
                }

            }
    }

    fun postLogin(userName:String,password:String)= fire(Dispatchers.IO){
        val userResponse = CourseNetwork.postLogin(userName,password)
        if (userResponse.code=="200"){
            val data=userResponse.data
            LogUtil.d("userData",data.toString())
            Result.success(data)
        }else{
            Result.failure(RuntimeException("response code is ${userResponse.code}"))
        }
    }

    fun postRegister(registerBody: RegisterBody)= fire(Dispatchers.IO){
        val userResponse=CourseNetwork.postRegister(registerBody)
        if (userResponse.code=="200"){
            val data=userResponse.code
            LogUtil.d("userData",data.toString())
            Result.success(data)
        }else{
            Result.failure(RuntimeException("response code is ${userResponse.code}"))
        }
    }

    fun getCoursesAttended(studentId:String)= fire(Dispatchers.IO){
        val coursesResponse=CourseNetwork.getCoursesAttended(studentId)
        if (coursesResponse.code=="200"){
            val data=coursesResponse.data
            LogUtil.d("coursesResponse.data",data.toString())
            Result.success(data)
        }else{
            Result.failure(RuntimeException("response code is ${coursesResponse.code}"))
        }
    }

    fun joinTheCourse(studentId:String,addClassCode:String)= fire(Dispatchers.IO){
        val joinTheCourseResponse=CourseNetwork.joinTheCourse(studentId,addClassCode)
        if (joinTheCourseResponse.code=="200"){
            val data=joinTheCourseResponse.code
            LogUtil.d("joinTheCourseResponse.code",data)
            Result.success(data)
        }else{
            Result.failure(RuntimeException("response code is ${joinTheCourseResponse.code}"))
        }
    }

    //????????????
    fun quitTheCourse(studentId: String,courseId: String)= fire(Dispatchers.IO){
        val quitTheCourseResponse=CourseNetwork.quitTheCourse(studentId, courseId)
        if (quitTheCourseResponse.code=="200"){
            val data=quitTheCourseResponse.code
            LogUtil.d("quitTheCourseResponseCode",data)
            Result.success(data)
        }else{
            Result.failure(RuntimeException("response code is ${quitTheCourseResponse.code}"))
        }
    }

    //????????????
    fun signInByStudent(signInByStudentBody: SignInByStudentBody)= fire(Dispatchers.IO){
        val genericResponse=CourseNetwork.signInByStudent(signInByStudentBody)
        if (genericResponse.code=="200"){
            val data=genericResponse.code
            LogUtil.d("genericResponse.code",data)
            Result.success(data)
        }else{
            Result.failure(RuntimeException("response code is ${genericResponse.code}"))
        }
    }

    fun signInByStudent(studentId: String,courseId: String,signInId: String,location:String,signInCode:String)= fire(Dispatchers.IO){
        val genericResponse=CourseNetwork.signInByStudent(studentId, courseId, signInId, location, signInCode)
        if (genericResponse.code=="200"){
            val data=genericResponse.code
            LogUtil.d("genericResponse.code",data)
            Result.success(data)
        }else{
            Result.failure(RuntimeException("response code is ${genericResponse.code}"))
        }
    }

    fun getSignInByStudent(studentId: String,courseId: String)= fire(Dispatchers.IO){
        val getSignInByStudentResponse=CourseNetwork.getSignInByStudent(studentId, courseId)
        if (getSignInByStudentResponse.code=="200"){
            val data=getSignInByStudentResponse.data
            LogUtil.d("getSignInByStudent.data",data.toString())
            Result.success(data)
        }else{
            Result.failure(RuntimeException("response code is ${getSignInByStudentResponse.code}"))
        }
    }

    fun createCourse(createCourseBody: CreateCourseBody)= fire(Dispatchers.IO){
        val courseForCreateResponse = CourseNetwork.createCourse(createCourseBody)
        if(courseForCreateResponse.code=="200"){
            val data=courseForCreateResponse.code
            Result.success(data)
        }else{
            Result.failure(RuntimeException("response code is ${courseForCreateResponse.code}"))
        }
    }

    //????????????????????????
    fun getCoursesCreated(id:String)= fire(Dispatchers.IO){
        val coursesResponse=CourseNetwork.getCoursesCreated(id)
        if (coursesResponse.code=="200"){
            val data=coursesResponse.data
            LogUtil.d("coursesResponse.data",data.toString())
            Result.success(data)
        }else{
            Result.failure(RuntimeException("response code is ${coursesResponse.code}"))
        }
    }

    //?????????????????????
    fun removeCourseCreated(id:String)= fire(Dispatchers.IO){
        val removeCourseCreatedResponse=CourseNetwork.removeCourseCreated(id)
        if (removeCourseCreatedResponse.code=="200"){
            val data=removeCourseCreatedResponse.code
            Result.success(data)
        }else{
            Result.failure(RuntimeException("response code is ${removeCourseCreatedResponse.code}"))
        }
    }

    //????????????
    fun startSignIn(startSignInBody: StartSignInBody)= fire(Dispatchers.IO){
        val genericResponse=CourseNetwork.startSignIn(startSignInBody)
        if (genericResponse.code=="200"){
            val data=genericResponse.code
            LogUtil.d("genericResponse.code",data)
            Result.success(data)
        }else{
            Result.failure(RuntimeException("response code is ${genericResponse.code}"))
        }
    }

    //????????????
    fun stopSignIn(signInId:String)= fire(Dispatchers.IO){
        val genericResponse=CourseNetwork.stopSignIn(signInId)
        if (genericResponse.code=="200"){
            val data=genericResponse.code
            LogUtil.d("genericResponse.code",data)
            Result.success(data)
        }else{
            Result.failure(RuntimeException("response code is ${genericResponse.code}"))
        }
    }

    //????????????????????????
    fun getSignInByTeacher(teacherId:String, courseId:String)= fire(Dispatchers.IO){
        val getSignInByTeacherResponse=CourseNetwork.getSignInByTeacher(teacherId, courseId)
        if (getSignInByTeacherResponse.code=="200"){
            val data=getSignInByTeacherResponse.data
            LogUtil.d("getSignInByTeacherResponse.data",data.toString())
            Result.success(data)
        }else{
            Result.failure(RuntimeException("response code is ${getSignInByTeacherResponse.code}"))
        }
    }

    //????????????????????????????????????
    fun getStudentSignInByTeacher(signInId: String)= fire(Dispatchers.IO){
        val getStudentSignInByTeacherResponse=CourseNetwork.getStudentSignInByTeacher(signInId)
        if (getStudentSignInByTeacherResponse.code=="200"){
            val data=getStudentSignInByTeacherResponse.data
            LogUtil.d("getStudentSignInByTeacherResponse.data",data.toString())
            Result.success(data)
        }else{
            Result.failure(RuntimeException("response code is ${getStudentSignInByTeacherResponse.code}"))
        }
    }

    private fun<T>fire(context: CoroutineContext, block:suspend ()->Result<T>)=
        liveData<Result<T>>(context){
            val result=try {
                block()
            }catch (e:Exception){
                Result.failure<T>(e)
            }
            emit(result)
        }

    fun savePlace(place: Place)= PlaceDao.savePlace(place)

    fun getPlace()=PlaceDao.getPlace()

    fun isPlaceSaved()=PlaceDao.isPlaceSaved()

    fun saveUser(user: User)= UserDao.saveUser(user)
    fun getUser()= UserDao.getUser()
    fun isUserSaved()= UserDao.isUserSaved()

    fun saveRememberPassword(boolean: Boolean)= UserDao.saveRememberPassword(boolean)
    fun getRememberPassword()= UserDao.getRememberPassword()
}