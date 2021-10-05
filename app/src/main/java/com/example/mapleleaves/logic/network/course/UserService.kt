package com.example.mapleleaves.logic.network.course

import com.example.mapleleaves.logic.model.*
import com.example.mapleleaves.logic.model.body.RegisterBody
import com.example.mapleleaves.logic.model.body.SignInByStudentBody
import com.example.mapleleaves.logic.model.body.StartSignInBody
import com.example.mapleleaves.logic.model.response.GenericResponse
import com.example.mapleleaves.logic.model.response.GetSignInByStudentResponse
import com.example.mapleleaves.logic.model.response.GetSignInByTeacherResponse
import com.example.mapleleaves.logic.model.response.GetStudentSignInByTeacherResponse
import retrofit2.Call
import retrofit2.http.*

interface UserService {

    //用户请求

    //用户登录
    @POST("login?")
    fun postLogin(@Query("username") userName:String, @Query("password") password:String ): Call<UserResponse>

    //用户注册
    @POST("register")
    fun postRegister(@Body registerBody: RegisterBody): Call<GenericResponse>

    //学生事务请求---------------------------------------------------------

    //获取加入的课程信息
    @GET("getCoursesAttended?")
    fun getCoursesAttended(@Query("studentId") studentId:String):Call<CoursesAttendedResponse>

    //加入课程
    @POST("joinTheCourse?")
    fun joinTheCourse(@Query("studentId") studentId: String,@Query("addClassCode") addClassCode:String):Call<JoinTheCourseResponse>

    //学生退出课堂 quitTheCourse?studentId=1800301331&courseId=1437397403991822338
    @POST("quitTheCourse?")
    fun quitTheCourse(@Query("studentId")studentId: String,@Query("courseId")courseId: String):Call<GenericResponse>

    //学生签到 signInByStudent,已废弃
    @POST("signInByStudent")
    fun signInByStudent(@Body signInByStudentBody: SignInByStudentBody):Call<GenericResponse>

    //获取签到记录
    @GET("getSignInByStudent?")
    fun getSignInByStudent(@Query("studentId")studentId: String,@Query("courseId")courseId: String):Call<GetSignInByStudentResponse>


    //老师事务请求---------------------------------------------------------

    //创建课堂
    @POST("createCourse")
    fun createCourse(@Body course: CourseForCreate):Call<CourseForCreateResponse>

    //查找创建的课程  getCoursesCreated?id=1800301331
    @GET("getCoursesCreated?")
    fun getCoursesCreated(@Query("id") id:String):Call<CoursesAttendedResponse>

    //删除创建的课程 removeCourseCreated?id=1442011759475978241
    @POST("removeCourseCreated?")
    fun removeCourseCreated(@Query("id")id:String):Call<RemoveCourseCreatedResponse>

    //开始签到
    @POST("startSignIn")
    fun startSignIn(@Body startSignInBody: StartSignInBody):Call<GenericResponse>

    //停止签到 stopSignIn?signInId=1441277313168732161
    @POST("stopSignIn?")
    fun stopSignIn(@Query("signInId") signInId:String):Call<GenericResponse>

    //老师获取签到记录 getSignInByTeacher?teacherId=1800301331&courseId=1437390830607310860
    @GET("getSignInByTeacher?")
    fun getSignInByTeacher(@Query("teacherId")teacherId:String,
                           @Query("courseId")courseId:String):Call<GetSignInByTeacherResponse>

    //老师获取学生具体签到记录 getStudentSignInByTeacher?signInId=1441277313168732161
    @GET("getStudentSignInByTeacher?")
    fun getStudentSignInByTeacher(@Query("signInId")signInId: String):Call<GetStudentSignInByTeacherResponse>

}