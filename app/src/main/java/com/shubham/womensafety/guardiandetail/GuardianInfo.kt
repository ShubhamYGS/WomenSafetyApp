package com.shubham.womensafety.guardiandetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.shubham.womensafety.R
import com.shubham.womensafety.database.GuardianDatabase
import com.shubham.womensafety.databinding.FragmentGuardianInfoBinding

class GuardianInfo : Fragment() {

    private lateinit var binding: FragmentGuardianInfoBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_guardian_info,container,false)

        val application = requireNotNull(this.activity).application

        val dataSource = GuardianDatabase.getInstance(application).guardianDatabaseDao

        val viewModelFactory = GuardianInfoViewModelFactory(dataSource, application)

        val guardianInfoViewModel =
            ViewModelProviders.of(
                this, viewModelFactory).get(GuardianInfoViewModel::class.java)

        binding.guardianInfoViewModel = guardianInfoViewModel

        binding.addGuardian.setOnClickListener { openAddGuardian() }

        return binding.root
    }

    fun openAddGuardian(){
        findNavController().navigate(GuardianInfoDirections.actionGuardianInfoToAddGuardian())
    }
}
