package com.example.mapleleaves.ui.weather

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.mapleleaves.R
import com.example.mapleleaves.databinding.ActivityWeatherBinding
import com.example.mapleleaves.databinding.FragmentWeatherBinding
import com.example.mapleleaves.logic.model.Weather
import com.example.mapleleaves.logic.model.getSky
import com.example.mapleleaves.utils.MyObserver
import com.example.mapleleaves.utils.showToast
import com.sunnyweather.android.ui.weather.WeatherViewModel
import java.text.SimpleDateFormat
import java.util.*

class WeatherFragment:Fragment() {

    val viewModel by lazy { ViewModelProviders.of(this).get(WeatherViewModel::class.java) }

    private var _binding:FragmentWeatherBinding?=null

    private val binding get() = _binding!!

    private val TAG=this::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(MyObserver(TAG))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentWeatherBinding.inflate(layoutInflater)

        viewModel.weatherLiveData.observe(viewLifecycleOwner, androidx.lifecycle.Observer {result->
            val weather=result.getOrNull()
            if (weather!=null){
                showWeatherInfo(weather)
            }else{
                "无法成功获取天气信息".showToast()
                result.exceptionOrNull()?.printStackTrace()
            }
            binding.swiperefresh.isRefreshing=false
        })
        //修改刷新图标圆圈的颜色
        binding.swiperefresh.setColorSchemeResources(R.color.theme)
        refreshWeather()
        // viewModel.refreshWeather(viewModel.locationLng,viewModel.locationLat)
        binding.swiperefresh.setOnRefreshListener {
            refreshWeather()
        }

//        activity?.window?.let {
//            it.decorView.systemUiVisibility=View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//            it.statusBarColor= Color.TRANSPARENT
//        }

        return binding.root
    }

    fun refreshWeather(){
        viewModel.getPlace()?.apply {
            viewModel.placeName=name
            location.apply {
                viewModel.locationLat=lat
                viewModel.locationLng=lng
            }
        }
        viewModel.refreshWeather(viewModel.locationLng,viewModel.locationLat)
        binding.swiperefresh.isRefreshing=true
    }

    private fun showWeatherInfo(weather: Weather){
        Log.d("weather",weather.toString())
        val realtime=weather.realtime
        val daily=weather.daily
        //填充nowXml布局中的数据
        binding.nowXml.navBtn.visibility=View.GONE
        binding.nowXml.placeName.text=viewModel.placeName
        val currentTempText="${realtime.temperature.toInt()}℃"
        binding.nowXml.currentTemp.text=currentTempText
        binding.nowXml.currentSky.text= getSky(realtime.skycon).info
        val currentPM25Text="空气指数${realtime.airQuality.aqi.chn.toInt()}"
        binding.nowXml.currentAQI.text=currentPM25Text
        binding.nowXml.nowLayout.setBackgroundResource(getSky(realtime.skycon).bg)
        //填充forecastXml布局中的数据
        binding.forecastXml.forecastLayout.removeAllViews()
        val days=daily.skycon.size
        for (i in 0 until days){
            val skycon=daily.skycon[i]
            val temperature=daily.temperature[i]
            val view=LayoutInflater.from(context).inflate(
                R.layout.forecast_item,
                binding.forecastXml.forecastLayout,false)
            val dateInfo=view.findViewById<TextView>(R.id.dateInfo)
            val skyIcon=view.findViewById<ImageView>(R.id.skyIcon)
            val skyInfo=view.findViewById<TextView>(R.id.skyInfo)
            val temperatureInfo=view.findViewById<TextView>(R.id.temperatureInfo)
            val simpleDateFormat= SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

            dateInfo.text=simpleDateFormat.format(skycon.date)
            val sky= getSky(skycon.value)
            skyIcon.setImageResource(sky.icon)
            skyInfo.text=sky.info
            val tempText="${temperature.min.toInt()}~${temperature.max.toInt()}℃"
            temperatureInfo.text=tempText
            binding.forecastXml.forecastLayout.addView(view)
        }
        //填充life_index.xml布局中的数据
        val lifeIndex=daily.lifeIndex
        binding.lifeIndexXml.coldRiskText.text=lifeIndex.coldRisk[0].desc
        binding.lifeIndexXml.dressingText.text=lifeIndex.dressing[0].desc
        binding.lifeIndexXml.ultravioletText.text=lifeIndex.ultraviolet[0].desc
        binding.lifeIndexXml.carWashingText.text=lifeIndex.carWashing[0].desc
        binding.weatherLayout.visibility= View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}