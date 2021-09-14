package com.example.mapleleaves.ui.course

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.mapleleaves.MainActivity
import com.example.mapleleaves.R
import com.example.mapleleaves.databinding.ActivityCheckWorkAttendanceBinding
import com.example.mapleleaves.databinding.ActivityCourseBinding
import com.example.mapleleaves.ui.checkWorkAttendance.CheckWorkAttendanceActivity
import com.example.mapleleaves.utils.MyObserver

class CourseActivity : AppCompatActivity() {

    lateinit var binding: ActivityCourseBinding

    private val TAG=this::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_course)
        val binding=ActivityCourseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Activity的布局会显示在状态栏上
        val decorView=window.decorView
        decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        // window.setDecorFitsSystemWindows(false)
        //将状态栏设置成透明色
        window.statusBarColor= Color.TRANSPARENT

        /*在view中android:fitsSystemWindows="true"设置此属性会出现的问题
        fitsSystemWindows属性可以让view根据系统窗口来调整自己的布局；简单点说就是我们在设置应用布局时是否考虑系统窗口布局，这里系统窗口包括系统状态栏、导航栏、输入法等，包括一些手机系统带有的底部虚拟按键。

        android:fitsSystemWindows=”true” （触发View的padding属性来给系统窗口留出空间）
        这个属性可以给任何view设置,只要设置了这个属性此view的其他所有padding属性失效，同时该属性的生效条件是只有在设置了透明状态栏(StatusBar)或者导航栏(NavigationBar)此属性才会生效。

        注意： fitsSystemWindows只作用在Android4.4及以上的系统，因为4.4以下的系统StatusBar没有透明状态。
         */

        binding.ivCheckWork.setOnClickListener {
            CheckWorkAttendanceActivity.startActivity(this)
        }

        lifecycle.addObserver(MyObserver(TAG))
    }

    companion object {
        fun startCourseActivity(context: Context){
            val intent= Intent(context, CourseActivity::class.java)
            //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }
}