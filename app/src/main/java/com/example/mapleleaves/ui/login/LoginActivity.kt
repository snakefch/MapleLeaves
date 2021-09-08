package com.example.mapleleaves.ui.login

import android.Manifest
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.mapleleaves.MainActivity
import com.example.mapleleaves.databinding.ActivityLoginBinding
import com.permissionx.guolindev.PermissionX

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    companion object{

        fun startLoginActivity(context: Context){
            val intent=Intent(context,LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_login)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val decorView=window.decorView
        decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        // window.setDecorFitsSystemWindows(false)
        window.statusBarColor= Color.TRANSPARENT


        //使用PermissionX获取权限
        PermissionX.init(this)
            .permissions(Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CAMERA)
            .onExplainRequestReason { scope, deniedList ->
                val message = "PermissionX需要您同意以下权限才能正常使用"
                scope.showRequestReasonDialog(deniedList, message, "Allow", "Deny")
            }
            .request { allGranted, grantedList, deniedList ->
                if (allGranted) {
                    Toast.makeText(this, "所有申请的权限都已通过", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "您拒绝了如下权限：$deniedList", Toast.LENGTH_SHORT).show()
                }
            }


        val sp=getSharedPreferences("user", MODE_PRIVATE)
        val isRemember=sp.getBoolean("remember_password",false)
        if (isRemember){
            val userName=sp.getString("userName","")
            val password=sp.getString("password","")
            binding.tvUserName.setText(userName)
            binding.password.setText(password)
            binding.cbRememberPass.isChecked=true
        }
        binding.login.setOnClickListener{
            val userName=binding.tvUserName.text.toString()
            val password=binding.password.text.toString()
            if (userName==null||userName.trim().isEmpty()){
                Toast.makeText(applicationContext,"用户名不能空", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (password==null||password.trim().isEmpty()){
                Toast.makeText(applicationContext,"密码不能空", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (userName.trim().length!==11||!userName.trim().startsWith("1")){
                Toast.makeText(applicationContext,"用户名不规范", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val editor=sp.edit()
            if (binding.cbRememberPass.isChecked){
                editor.putBoolean("remember_password",true)
                editor.putString("userName",userName)
                editor.putString("password",password)
            }else{
                editor.clear()
            }
            editor.apply()

            MainActivity.startMainActivity(this )

           // val intent=Intent(this, MainActivity::class.java)
           // startActivity(intent)
            // editor.commit()//commit 与 apply有所区别
            //login()
            //finish()
        }
    }
}