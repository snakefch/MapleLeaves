package com.example.mapleleaves.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.mapleleaves.databinding.FragmentHomeBinding
import com.example.mapleleaves.utils.MyObserver
import com.google.android.material.tabs.TabLayout

class HomeFragment : Fragment() {

   // private lateinit var homeViewModel: HomeViewModel
    // 下面这种方式与上面的区别？？？
  //  private val homeViewModel by lazy { ViewModelProviders.of(this).get(HomeViewModel::class.java) }
//
//    private lateinit var listAdapter: CourseListAdapter

   /* private lateinit var studyListAdapter: CourseListAdapter
    private lateinit var teachListAdapter: CourseListAdapter*/

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
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
//        homeViewModel =
//           ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //初始,第一次的方法
//        val layoutManager=LinearLayoutManager(activity)
//        binding.rvCourse.layoutManager=layoutManager
//        listAdapter= CourseListAdapter(this,homeViewModel.courseList)
//        binding.rvCourse.adapter=listAdapter
//        homeViewModel.courseList.add(Course("Android应用开发","2023280",80))
//        homeViewModel.courseList.add(Course("课程名","课程描述",81))
//        homeViewModel.courseList.add(Course("课程名","课程描述",60))
//        listAdapter.notifyDataSetChanged()


        //第二次，使用viewPage,有Bug
        val sectionsPagerAdapter =
            this.context?.let { SectionsPagerAdapter(it, childFragmentManager) }
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)

        //第三次,使用两个recycleView,不行
      /*  val layoutManager=LinearLayoutManager(activity)
        binding.incldueStudy.rvStudyCourse.layoutManager=layoutManager
        studyListAdapter= CourseListAdapter(this,homeViewModel.studyCourseList)
        binding.incldueStudy.rvStudyCourse.adapter=studyListAdapter
        studyListAdapter.notifyDataSetChanged()

        val teachLayoutManager=LinearLayoutManager(activity)
        binding.incldueTeach.rvTeachCourse.layoutManager=teachLayoutManager
        teachListAdapter= CourseListAdapter(this,homeViewModel.teachCourseList)
        binding.incldueStudy.rvStudyCourse.adapter=teachListAdapter
        teachListAdapter.notifyDataSetChanged()*/

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}