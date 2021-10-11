package com.example.mapleleaves.ui.studentsignin

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mapleleaves.databinding.ActivityStudentSignInBinding
import com.example.mapleleaves.utils.AddressUtil
import com.example.mapleleaves.utils.showToast

class StudentSignInActivity : AppCompatActivity() {

    private lateinit var binding:ActivityStudentSignInBinding

    private val viewModel by lazy { ViewModelProviders.of(this).get(StudentSignInViewModel::class.java) }

    private val TAG=this.javaClass.simpleName

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityStudentSignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val studentId=intent.getStringExtra("studentId")!!
        val courseId=intent.getStringExtra("courseId")!!
        val signInId=intent.getStringExtra("signInId")!!
        var signInLocation=""

        val locationManager=getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (locationManager!=null){
            Log.d(TAG,"locationManager is no null")
        }
        var location=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        if (location==null){
            Log.d(TAG,"location is null")
            location=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        }
        location?.let {
            val lat=it.latitude
            val lng=it.longitude
            signInLocation=AddressUtil.getAddress(lat,lng)
            Log.d(TAG,"lat is $lat;lng is $lng;featureName is $signInLocation")
        }

        binding.btDetermine.setOnClickListener {
            viewModel.refresh(studentId,courseId,signInId,signInLocation,binding.tvSignInCode.text.toString())
        }

        viewModel.resultLiveData.observe(this, Observer {
            val result=it.getOrNull()
            if (result!=null){
                "签到成功".showToast()
            }else{
                "签到失败".showToast()
            }
            finish()
        })
    }

    companion object{
        fun startActivity(context: Context, studentId: String,courseId: String,signInId: String){
            val intent= Intent(context, StudentSignInActivity::class.java).apply {
                putExtra("studentId",studentId)
                putExtra("courseId",courseId)
                putExtra("signInId",signInId)
            }
            context.startActivity(intent)
        }
    }
}