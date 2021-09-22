package com.example.mapleleaves.ui.place

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mapleleaves.R
import com.example.mapleleaves.databinding.FragmentPlaceBinding
import com.example.mapleleaves.ui.weather.WeatherFragment

class PlaceFragment:Fragment() {

    val viewModel by lazy { ViewModelProviders.of(this).get(PlaceViewModel::class.java) }

    private lateinit var adapter: PlaceAdapter

    private var _binding: FragmentPlaceBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentPlaceBinding.inflate(inflater,container,false)

        activity?.window?.let {
            it.decorView.systemUiVisibility=View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            it.statusBarColor= Color.TRANSPARENT
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        /*if (activity is MainActivity && viewModel.isPalceSaved()){
            val place=viewModel.getPlace()
            val intent= Intent(context, WeatherActivity::class.java).apply {
                putExtra("location_lng",place.location.lng)
                putExtra("location_lat",place.location.lat)
                putExtra("place_name",place.name)
            }
            startActivity(intent)
            //activity?.finish()
            return
        }*/
        val layoutManager=LinearLayoutManager(activity)
        binding.recyclerView.layoutManager=layoutManager
        adapter= PlaceAdapter(this,viewModel.placeList)
        binding.recyclerView.adapter=adapter
        binding.searchPlaceEdit.addTextChangedListener { editable->
            binding.actionBarLayout.setBackgroundColor(resources.getColor(R.color.theme))
            activity?.window?.statusBarColor=resources.getColor(R.color.theme)
            val content=editable.toString()
            if (content.isNotEmpty()){
                viewModel.searchPlaces(content)
                Log.d("PlaceFragmentContent",content)
            }   else{
                binding.actionBarLayout.setBackgroundColor(Color.TRANSPARENT)
                activity?.window?.statusBarColor=Color.TRANSPARENT
                binding.recyclerView.visibility=View.GONE
                binding.bgImageView.visibility=View.VISIBLE
                binding.frameLayoutWeather.visibility=View.VISIBLE
                viewModel.placeList.clear()
                adapter.notifyDataSetChanged()
            }
        }
        //viewLifecycleOwner 代替this
        viewModel.placeLiveData.observe(viewLifecycleOwner, Observer { result->
            val places=result.getOrNull()
            if (places!=null){
                binding.recyclerView.visibility=View.VISIBLE
                binding.bgImageView.visibility=View.GONE
                binding.frameLayoutWeather.visibility=View.GONE
                viewModel.placeList.clear()
                viewModel.placeList.addAll(places)
                adapter.notifyDataSetChanged()
            }else{
                Toast.makeText(activity,"未能查询到任何地点",Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })

    }

    fun stopSearch(){
        //收起软键盘
        val inputMethodManager= context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(binding.searchPlaceEdit.windowToken,0)
        //清空editText内容
        binding.searchPlaceEdit.setText("")
        //获取WeatherFragment实例，并调用其refreshWeather()方法刷新天气
        val weatherFragment=childFragmentManager.findFragmentById(R.id.weatherFragment) as WeatherFragment
        weatherFragment.refreshWeather()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}