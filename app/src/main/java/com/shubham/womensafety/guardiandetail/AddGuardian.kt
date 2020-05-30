package com.shubham.womensafety.guardiandetail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.room.Room
import com.shubham.womensafety.R
import com.shubham.womensafety.database.Guardian
import com.shubham.womensafety.database.GuardianDatabase
import com.shubham.womensafety.databinding.FragmentAddGuardianBinding
import kotlinx.coroutines.*

class AddGuardian : Fragment() {

    private lateinit var binding: FragmentAddGuardianBinding
    private lateinit var model: GuardianInfoViewModel
    private lateinit var name:String
    private lateinit var relation:String
    private lateinit var email:String
    private lateinit var phone:String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_add_guardian, container, false
        )

        // Get the view model
        model = ViewModelProviders.of(this).get(GuardianInfoViewModel::class.java)

        binding.submitDetail.setOnClickListener {
            addData()
        }

        return binding.root
    }

    private fun addData(){
        name = binding.editName.text.toString()
        relation = binding.editEmail.text.toString()
        phone = binding.editPhone.text.toString()
        email = binding.editRelation.text.toString()

        val guardian = Guardian(null,name,relation,phone,email)
        model.insert(guardian)

        Toast.makeText(activity!!,"Data Inserted Succesfully",Toast.LENGTH_SHORT).show()
    }
}
