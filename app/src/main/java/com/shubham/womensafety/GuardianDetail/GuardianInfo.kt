package com.shubham.womensafety.GuardianDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.shubham.womensafety.R
import com.shubham.womensafety.databinding.FragmentGuardianInfoBinding

class GuardianInfo : Fragment() {

    private lateinit var binding: FragmentGuardianInfoBinding

    private lateinit var viewModel: GuardianInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_guardian_info,container,false)

        viewModel = ViewModelProviders.of(this).get(GuardianInfoViewModel::class.java)

        binding.addGuardian.setOnClickListener { openAddGuardian() }

        return binding.root
    }

    fun openAddGuardian(){
        findNavController().navigate(GuardianInfoDirections.actionGuardianInfoToAddGuardian())
    }
}
