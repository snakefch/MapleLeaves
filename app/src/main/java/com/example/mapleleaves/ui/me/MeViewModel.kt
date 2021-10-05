package com.example.mapleleaves.ui.me

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mapleleaves.logic.Repository
import com.example.mapleleaves.logic.model.User

class MeViewModel : ViewModel() {

   fun getUser(): User {
      return Repository.getUser()
   }

}