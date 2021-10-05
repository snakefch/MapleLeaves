package com.example.mapleleaves.logic.model.response

//老师获取签到记录Response

data class GetSignInByTeacherResponse (val code:String,val msg:String,val data:List<SignInRecord>){
    data class SignInRecord(val id:String,val name:String,val courseId:String,val courseName:String,
                            val teacherId:String, val teacherName:String,val datetime:String,
                            val attendanceNumber:Int,val truanceNumber:Int,val lateNumber:Int,
                            val leaveEarlyNumber:Int,val askForLeaveNumber:Int,val totalNumber:Int,val signInCode:String,val state:String)
}