package com.example.mapleleaves.logic.dao

import android.content.Context
import androidx.core.content.edit
import com.example.mapleleaves.MapleLeavesApplication
import com.example.mapleleaves.logic.model.User
import com.google.gson.Gson

object UserDao {

    private fun sharedPreferences()= MapleLeavesApplication.context.
    getSharedPreferences("user", Context.MODE_PRIVATE)

    fun saveUser(user: User){
        sharedPreferences().edit{
            putString("user", Gson().toJson(user))
        }
    }

    fun getUser(): User {
        val userJson= sharedPreferences().getString("user","")
        return Gson().fromJson(userJson, User::class.java)
    }

    fun isUserSaved()= sharedPreferences().contains("user")

    fun saveRememberPassword(boolean: Boolean){
        sharedPreferences().edit{
            putBoolean("remember_password",boolean)
        }
    }

    fun getRememberPassword(): Boolean {
        return sharedPreferences().getBoolean("remember_password",false)
    }


}