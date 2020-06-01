package com.shubham.womensafety.guardiandetail

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.shubham.womensafety.R
import com.shubham.womensafety.database.Guardian
import com.shubham.womensafety.databinding.FragmentAddGuardianBinding

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
        if(TextUtils.isEmpty(binding.editName.text.toString())){
            binding.editName.setError("This field cannot be empty")
            return
        }
        else if(TextUtils.isEmpty(binding.editRelation.text.toString())){
            binding.editRelation.setError("This field cannot be empty")
            return
        }
        else if(TextUtils.isEmpty(binding.editPhone.text.toString())){
            binding.editPhone.setError("This field cannot be empty")
            return
        }
        else if(TextUtils.isEmpty(binding.editEmail.text.toString())){
            binding.editEmail.setError("This field cannot be empty")
            return
        }
        name = binding.editName.text.toString()
        relation = binding.editRelation.text.toString()
        phone = binding.editPhone.text.toString()
        email = binding.editEmail.text.toString()

        val guardian = Guardian(null,name,relation,phone,email)
        model.insert(guardian)

        Toast.makeText(activity!!,"Data Inserted Successfully",Toast.LENGTH_SHORT).show()

        findNavController().navigate(AddGuardianDirections.actionAddGuardianToGuardianInfo())

    }
}
