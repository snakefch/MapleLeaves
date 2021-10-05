package com.example.mapleleaves.ui.me

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mapleleaves.databinding.FragmentMeBinding
import com.example.mapleleaves.ui.personaldata.PersonalDataActivity
import com.example.mapleleaves.utils.MyObserver

class MeFragment : Fragment() {

    private lateinit var meViewModel: MeViewModel
    private var _binding: FragmentMeBinding? = null

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
        meViewModel =
            ViewModelProvider(this).get(MeViewModel::class.java)

        _binding = FragmentMeBinding.inflate(inflater, container, false)

        val user=meViewModel.getUser()

        binding.includeMeHeader.apply {
            tvUserName.text=user.name
            tvUserSchool.text=user.college
        }

        binding.itemPersonalData.setOnClickListener {
            context?.let { it1 -> PersonalDataActivity.actionStart(it1) }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}