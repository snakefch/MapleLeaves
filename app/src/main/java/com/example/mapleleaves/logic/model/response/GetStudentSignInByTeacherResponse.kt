package com.example.mapleleaves.logic.model.response

//老师获取学生签到具体记录Response

data class GetStudentSignInByTeacherResponse(val code:String,val msg:String,val data:List<StudentSignInRecord>){
    data class StudentSignInRecord(val id:String,val studentId:String,val studentName:String,val courseId:String,
                                   val courseName:String,val signInId:String,val signInName:String,val location:String,
                                   val datetime:String,val result:String,val state:String)
}