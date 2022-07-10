package com.top1shvetsvadim1.testebs.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.top1shvetsvadim1.testebs.data.checkInternetStatus.CheckInternetStatus
import com.top1shvetsvadim1.testebs.databinding.FragmentFlowActivityBinding
import java.lang.RuntimeException

class FlowActivityFragment : Fragment() {

    private var _binding: FragmentFlowActivityBinding? = null
    private val binding: FragmentFlowActivityBinding
        get() = _binding ?: throw RuntimeException("FragmentFlowActivityBinding == null")

    private val checkInternetStatus = CheckInternetStatus()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkInternetStatus.checkInternet(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFlowActivityBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
    }
}