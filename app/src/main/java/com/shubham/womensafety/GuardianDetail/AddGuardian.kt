package com.shubham.womensafety.GuardianDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.shubham.womensafety.R
import com.shubham.womensafety.databinding.FragmentAddGuardianBinding

class AddGuardian : Fragment() {

    private lateinit var binding: FragmentAddGuardianBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_add_guardian,container,false)

        return binding.root
    }

}
