package com.example.mapleleaves.ui.joinCourse

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.mapleleaves.logic.Repository
import com.example.mapleleaves.logic.model.User

class JoinClassViewModel: ViewModel() {

    private val codeLiveData= MutableLiveData<String>()

    val resultLiveData= Transformations.switchMap(codeLiveData){ code->
        Repository.joinTheCourse(Repository.getUser().id,code)
    }

    fun setAddClassCode(addClassCode:String){
       codeLiveData.value= addClassCode
    }

}