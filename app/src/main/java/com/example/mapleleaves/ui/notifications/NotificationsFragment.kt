package com.example.mapleleaves.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mapleleaves.R
import com.example.mapleleaves.databinding.FragmentNotificationsBinding
import com.example.mapleleaves.ui.weather.WeatherFragment
import com.example.mapleleaves.utils.MyObserver

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private var _binding: FragmentNotificationsBinding? = null

    private val TAG=this::class.java.simpleName

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(MyObserver(TAG))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //测试使用子fragment显示天气数据
        //如何获取子Fragment的实例，并调用其方法
//        val weatherFragment=childFragmentManager.findFragmentById(R.id.weatherFragment) as WeatherFragment
//        binding.btTest.setOnClickListener {
//            weatherFragment.refreshWeather()
//            binding.frameLayoutWeather.visibility=View.GONE
//        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}