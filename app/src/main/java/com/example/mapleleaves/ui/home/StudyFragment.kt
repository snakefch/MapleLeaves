package com.example.mapleleaves.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mapleleaves.databinding.FragmentStudyBinding
import com.example.mapleleaves.utils.LogUtil
import com.example.mapleleaves.utils.MyObserver

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StudyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StudyFragment : Fragment() {

    private var _binding:FragmentStudyBinding?=null
    private val binding get() = _binding!!

    private val TAG=this::class.java.simpleName

    private val studyViewModel by lazy { ViewModelProviders.of(this).get(StudyViewModel::class.java) }
    private lateinit var studyListAdapter: CourseListAdapter

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

        _binding= FragmentStudyBinding.inflate(inflater,container,false)

        val layoutManager=LinearLayoutManager(activity)
        binding.rvStudyCourse.layoutManager=layoutManager

        studyViewModel.setStudentId("1800301331")

        studyViewModel.stuCoursesLiveData.observe(viewLifecycleOwner, Observer { result->
            val stuCourses=result.getOrNull()
            if (stuCourses!=null){
                LogUtil.d(TAG,stuCourses.toString())
                studyViewModel.stuCoursesList.addAll(stuCourses)
                studyListAdapter= CourseListAdapter(this,studyViewModel.stuCoursesList)
                binding.rvStudyCourse.adapter=studyListAdapter
                studyListAdapter.notifyDataSetChanged()
            }else{
                LogUtil.d(TAG,"课程数据为空")
            }

        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
         * @return A new instance of fragment StudyFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StudyFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}