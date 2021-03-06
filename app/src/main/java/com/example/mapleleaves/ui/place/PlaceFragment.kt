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
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mapleleaves.R
import com.example.mapleleaves.databinding.FragmentPlaceBinding
import com.example.mapleleaves.ui.weather.WeatherFragment
import com.example.mapleleaves.utils.LogUtil
import com.example.mapleleaves.utils.MyObserver

class PlaceFragment:Fragment() {

    val viewModel by lazy { ViewModelProviders.of(this).get(PlaceViewModel::class.java) }

    private lateinit var adapter: PlaceAdapter

    private var _binding: FragmentPlaceBinding?=null
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
        _binding= FragmentPlaceBinding.inflate(inflater,container,false)

        activity?.window?.let {
            it.decorView.systemUiVisibility=View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            it.statusBarColor= Color.TRANSPARENT
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val layoutManager=LinearLayoutManager(activity)
        binding.recyclerView.layoutManager=layoutManager
        adapter= PlaceAdapter(this,viewModel.placeList)
        binding.recyclerView.adapter=adapter
        binding.searchPlaceEdit.addTextChangedListener { editable->
            context?.let { ContextCompat.getColor(it,R.color.theme) }?.let {
                binding.actionBarLayout.setBackgroundColor(
                    it
                )
                activity?.window?.statusBarColor=it
            }
            val content=editable.toString()
            if (content.isNotEmpty()){
                LogUtil.d(TAG,"????????????????????????$content")
                viewModel.searchPlaces(content)
                binding.searchPlaceEdit.background= context?.let { ActivityCompat.getDrawable(it,R.drawable.shape_edit_all_circular) }
            }   else{
                LogUtil.d(TAG,"???????????????????????????$content")
                binding.searchPlaceEdit.background=context?.let { ActivityCompat.getDrawable(it,R.drawable.shape_edit_transparent_all_circle) }
                binding.actionBarLayout.setBackgroundColor(Color.TRANSPARENT)
                activity?.window?.statusBarColor=Color.TRANSPARENT
                binding.recyclerView.visibility=View.GONE
                binding.bgImageView.visibility=View.VISIBLE
                binding.frameLayoutWeather.visibility=View.VISIBLE
                viewModel.placeList.clear()
                adapter.notifyDataSetChanged()
            }
        }
        //viewLifecycleOwner ??????this
        viewModel.placeLiveData.observe(viewLifecycleOwner, Observer { result->
            var places=result.getOrNull()
            if (places!=null){
                LogUtil.d(TAG,"place?????????$places")
                binding.recyclerView.visibility=View.VISIBLE
                binding.bgImageView.visibility=View.GONE
                binding.frameLayoutWeather.visibility=View.GONE
                viewModel.placeList.clear()
                viewModel.placeList.addAll(places)
                adapter.notifyDataSetChanged()
            }else{
                Toast.makeText(activity,"???????????????????????????",Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })

    }

    override fun onResume() {
        super.onResume()
        //B01???????????????:????????????place??????????????????editText?????????????????????????????????placeLivedata?????????????????????????????????????????????????????????????????????????????????Bug,Bug???????????????????????????
        binding.searchPlaceEdit.setText("")
    }

    fun stopSearch(){
        //???????????????
        val inputMethodManager= context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(binding.searchPlaceEdit.windowToken,0)
        //??????editText??????
        binding.searchPlaceEdit.setText("")
        //?????????????????? android:focusable="true" android:focusableInTouchMode="true" ??????clearFocus()??????????????????
        binding.searchPlaceEdit.clearFocus()
        //??????WeatherFragment?????????????????????refreshWeather()??????????????????
        val weatherFragment=childFragmentManager.findFragmentById(R.id.weatherFragment) as WeatherFragment
        weatherFragment.refreshWeather()
    }

    override fun onPause() {
        super.onPause()
        activity?.window?.let {
            it.statusBarColor= context?.let { it1 -> ContextCompat.getColor(it1,R.color.theme) }!!
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}