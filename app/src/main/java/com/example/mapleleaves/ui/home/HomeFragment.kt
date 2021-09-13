package com.example.mapleleaves.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.example.mapleleaves.databinding.FragmentHomeBinding
import com.example.mapleleaves.logic.model.Course
import com.google.android.material.tabs.TabLayout

class HomeFragment : Fragment() {

   // private lateinit var homeViewModel: HomeViewModel

    //下面这种方式与上面的区别？？？
//    private val homeViewModel by lazy { ViewModelProviders.of(this).get(HomeViewModel::class.java) }
//
//    private lateinit var listAdapter: CourseListAdapter

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        homeViewModel =
//           ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
//        val layoutManager=LinearLayoutManager(activity)
//        binding.rvCourse.layoutManager=layoutManager
//        listAdapter= CourseListAdapter(this,homeViewModel.courseList)
//        binding.rvCourse.adapter=listAdapter
//        homeViewModel.courseList.add(Course("Android应用开发","2023280",80))
//        homeViewModel.courseList.add(Course("课程名","课程描述",81))
//        homeViewModel.courseList.add(Course("课程名","课程描述",60))
//        listAdapter.notifyDataSetChanged()

        val sectionsPagerAdapter =
            this.context?.let { parentFragmentManager?.let { it1 -> SectionsPagerAdapter(it, it1) } }
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)

        Log.d("HomeFragmentLife","onCreateView")
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        val sectionsPagerAdapter =
//            this.context?.let { parentFragmentManager?.let { it1 -> SectionsPagerAdapter(it, it1) } }
//        val viewPager: ViewPager = binding.viewPager
//        viewPager.adapter = sectionsPagerAdapter
//        val tabs: TabLayout = binding.tabs
//        tabs.setupWithViewPager(viewPager)
        Log.d("HomeFragmentLife","onActivityCreated")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("HomeFragmentLife","onDestroy")
        _binding = null
    }

    override fun onDetach() {
        Log.d("HomeFragmentLife","onDetach")
        super.onDetach()
    }
}