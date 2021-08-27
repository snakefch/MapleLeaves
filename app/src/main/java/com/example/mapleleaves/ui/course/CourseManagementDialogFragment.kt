package com.example.mapleleaves.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mapleleaves.R
import com.example.mapleleaves.databinding.ActivityMainBinding.inflate
import com.example.mapleleaves.databinding.CourseManagementDialogFragmentBinding
import com.example.mapleleaves.databinding.FragmentDashboardBinding
import com.example.mapleleaves.ui.course.CourseManagementDialogViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CourseManagementDialogFragment : BottomSheetDialogFragment() {

    private lateinit var courseManagementDialogViewModel: CourseManagementDialogViewModel

    private  var _binding: CourseManagementDialogFragmentBinding?=null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        courseManagementDialogViewModel =
            ViewModelProvider(this).get(CourseManagementDialogViewModel::class.java)

        _binding = CourseManagementDialogFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}