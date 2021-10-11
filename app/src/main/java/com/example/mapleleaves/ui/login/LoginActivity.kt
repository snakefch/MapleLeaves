package com.example.mapleleaves.ui.login

import android.Manifest
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mapleleaves.MainActivity
import com.example.mapleleaves.databinding.ActivityLoginBinding
import com.example.mapleleaves.logic.model.User
import com.example.mapleleaves.ui.place.PlaceViewModel
import com.example.mapleleaves.ui.register.RegisterActivity
import com.example.mapleleaves.utils.LogUtil
import com.example.mapleleaves.utils.MyObserver
import com.permissionx.guolindev.PermissionX

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    val viewModel by lazy { ViewModelProviders.of(this).get(LoginViewModel::class.java) }

    private val TAG=this::class.java.simpleName

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
            .permissions(Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CAMERA,Manifest.permission.ACCESS_COARSE_LOCATION)
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

        val isRemember=viewModel.getRememberPassword()
        if (isRemember){
            val userName=viewModel.getUser().userName
            val password=viewModel.getUser().password
            binding.tvUserName.setText(userName)
            binding.tvPassword.setText(password)
            binding.cbRememberPass.isChecked=true
        }
        binding.login.setOnClickListener{
            val userName=binding.tvUserName.text.toString()
            val password=binding.tvPassword.text.toString()
//            if (userName==null||userName.trim().isEmpty()){
//                Toast.makeText(applicationContext,"用户名不能空", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
//            if (password==null||password.trim().isEmpty()){
//                Toast.makeText(applicationContext,"密码不能空", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
//            if (userName.trim().length!==11||!userName.trim().startsWith("1")){
//                Toast.makeText(applicationContext,"用户名不规范", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }

            viewModel.postLogin(userName,password)

        }

        lifecycle.addObserver(MyObserver(TAG))

        binding.tvRegister.setOnClickListener {
            RegisterActivity.startActivity(this)
        }
//        binding.loginTest.setOnClickListener {
////            val data =Repository.postLogin("1800301333","123456")
////            LogUtil.d("data",data.toString())
//
//            //需要使用下面这种方式，修改数据加监听组合，只修改数据无效；上面的方式无效。仓库层只有ViewModel层持有，且为单向，例如
//            仓库层不能持有ViewModel层的引用。并且引用不能跨层持有
//            viewModel.postLogin("1800301333")
//        }

        viewModel.userLiveData.observe(this, Observer { result->
            val userData=result.getOrNull()
            if (userData!=null){
                if (binding.cbRememberPass.isChecked){
                    viewModel.saveRememberPassword(true)
                    viewModel.saveUser(User(userData.id,userData.userName,userData.password,userData.name,userData.gender))
                }else{
                    viewModel.saveRememberPassword(false)
                }
                MainActivity.startMainActivity(this )
                Log.d("用户数据",userData.toString())
                finish()
            }else{
                Toast.makeText(this,"登录失败",Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })

    }
}