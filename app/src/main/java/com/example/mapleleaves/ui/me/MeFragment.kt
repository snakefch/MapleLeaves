package com.example.mapleleaves.ui.me

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.mapleleaves.R
import com.example.mapleleaves.databinding.FragmentHomeBinding
import com.example.mapleleaves.databinding.FragmentMeBinding
import com.example.mapleleaves.ui.home.HomeViewModel
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
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}