package com.example.mapleleaves.ui.signinsheet

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.mapleleaves.logic.Repository
import com.example.mapleleaves.logic.model.body.StartSignInBody
import com.example.mapleleaves.logic.model.response.GetSignInByTeacherResponse

class SignInSheetViewModel:ViewModel() {

    val signInRecodeList=ArrayList<GetSignInByTeacherResponse.SignInRecord>()

    private val courseIdLiveData=MutableLiveData<String>()

    fun setCourseIdLiveData(courseId:String){
        courseIdLiveData.value=courseId
    }

    val resultLiveData=Transformations.switchMap(courseIdLiveData){courseId->
        Repository.getSignInByTeacher(Repository.getUser().id,courseId)
    }

}