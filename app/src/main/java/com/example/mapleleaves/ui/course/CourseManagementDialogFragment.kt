package com.example.mapleleaves.ui.course

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.mapleleaves.R
import com.example.mapleleaves.databinding.CourseManagementDialogFragmentBinding
import com.example.mapleleaves.utils.LogUtil
import com.example.mapleleaves.utils.Notifier
import com.example.mapleleaves.utils.showToast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CourseManagementDialogFragment : BottomSheetDialogFragment() {

    private lateinit var courseManagementDialogViewModel: CourseManagementDialogViewModel

    private  var _binding: CourseManagementDialogFragmentBinding?=null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private enum class Identity {
        Student,
        Teacher
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL,R.style.AppBottomSheet)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        courseManagementDialogViewModel =
            ViewModelProvider(this).get(CourseManagementDialogViewModel::class.java)

        _binding = CourseManagementDialogFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val args:CourseManagementDialogFragmentArgs by navArgs()

        if (args.courseId>0){
            LogUtil.d("args.itemId:","${args.courseId}")
        }

        val identity= if (args.identity==1) Identity.Student else Identity.Teacher

        if (identity== Identity.Student){
            binding.tvDropOut.setOnClickListener {
                courseManagementDialogViewModel.setStuCourseId(args.courseId.toString())
            }

            courseManagementDialogViewModel.quitResultLiveData.observe(this, Observer {
                val result=it.getOrNull()
                if (result!=null){
                    "成功退出课程".showToast()
                    val intent=Intent("com.example.mapleleaves.RefreshStudentList")
                    activity?.sendBroadcast(intent)
                }else{
                    "退出失败".showToast()
                }
                dismiss()
            })

        }else{
            binding.tvDropOut.apply {
                text="删除课程"
                setOnClickListener {
                    courseManagementDialogViewModel.setTeaCourseId(args.courseId.toString())
                }
            }

            courseManagementDialogViewModel.removeResultLiveData.observe(this, Observer {
                val result=it.getOrNull()
                if (result!=null){
                    "成功删除课程".showToast()
                    val intent=Intent("com.example.mapleleaves.RefreshTeacherList")
                    activity?.sendBroadcast(intent)
//                    dismiss()
//                val parent=parentFragment as TeachFragment
//                parent.refreshList()
                }else{
                    "删除失败".showToast()
                }
                dismiss()
            })
        }

        binding.cancel.setOnClickListener {
            dismiss()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}