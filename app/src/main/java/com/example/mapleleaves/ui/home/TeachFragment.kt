package com.example.mapleleaves.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mapleleaves.R
import com.example.mapleleaves.databinding.FragmentTeachBinding
import com.example.mapleleaves.ui.createCourse.CreateCourseActivity
import com.example.mapleleaves.utils.LogUtil
import com.example.mapleleaves.utils.MyObserver

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TeachFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TeachFragment : Fragment() {

    private var _binding:FragmentTeachBinding?=null
    private val binding get() = _binding!!

    private val teacherViewModel by lazy { ViewModelProviders.of(this).get(TeachViewModel::class.java) }
    private lateinit var teachListAdapter: CourseListAdapter

    private val TAG=this::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        lifecycle.addObserver(MyObserver(TAG))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentTeachBinding.inflate(inflater,container,false)

        val layoutManager= LinearLayoutManager(activity)
        binding.rvTeachCourse.layoutManager=layoutManager

        teacherViewModel.setTeacherId("1800301331")

        teacherViewModel.teaCoursesLiveData.observe(viewLifecycleOwner, Observer { result->
            val teaCourses=result.getOrNull()
            if (teaCourses!=null){
                LogUtil.d(TAG,teaCourses.toString())
                teacherViewModel.teachCoursesList.addAll(teaCourses)
                teachListAdapter= CourseListAdapter(this,teacherViewModel.teachCoursesList)
                binding.rvTeachCourse.adapter=teachListAdapter
                teachListAdapter.notifyDataSetChanged()
            }else{
                LogUtil.d(TAG,"课程数据为空")
            }

        })

        binding.tvCreateCourse.setOnClickListener {
            context?.let { it1 -> CreateCourseActivity.startCreateCourseActivity(it1) }
        }

        return binding.root
    }

    companion object {

        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        // TODO: Rename and change types of parameters
        private var param1: String? = null
        private var param2: String? = null

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TeachFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TeachFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}