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

class MeFragment : Fragment() {

    private lateinit var meViewModel: MeViewModel
    private var _binding: FragmentMeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        meViewModel =
            ViewModelProvider(this).get(MeViewModel::class.java)

        _binding = FragmentMeBinding.inflate(inflater, container, false)
        val root: View = binding.root
/*
        val textView: TextView = binding.textMe
        meViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
        Log.d("MeFragmentLife","onCreateView")
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("MeFragmentLife","onDestroy")
        _binding = null
    }

    override fun onDetach() {
        Log.d("MeFragmentLife","onDetach")
        super.onDetach()
    }

}