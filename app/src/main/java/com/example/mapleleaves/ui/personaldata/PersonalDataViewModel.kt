package com.example.mapleleaves.ui.personaldata

import androidx.lifecycle.ViewModel
import com.example.mapleleaves.logic.Repository
import com.example.mapleleaves.logic.model.User

class PersonalDataViewModel:ViewModel() {

    fun getUser(): User {
        return Repository.getUser()
    }


}