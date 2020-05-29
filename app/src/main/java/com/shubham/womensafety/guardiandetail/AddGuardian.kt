package com.shubham.womensafety.guardiandetail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.room.Room
import com.shubham.womensafety.R
import com.shubham.womensafety.database.Guardian
import com.shubham.womensafety.database.GuardianDatabase
import com.shubham.womensafety.databinding.FragmentAddGuardianBinding
import kotlinx.coroutines.*

class AddGuardian : Fragment() {

    private lateinit var binding: FragmentAddGuardianBinding

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_add_guardian, container, false
        )


        binding.submitDetail.setOnClickListener {
            uiScope.launch {
                addToDb()
            }
        }

        return binding.root
    }

    private suspend fun addToDb() {
        withContext(Dispatchers.IO) {
            val db =
                Room.databaseBuilder(activity!!, GuardianDatabase::class.java, "GuardianDB").build()

            val guardian = Guardian()
            guardian.guardianName = binding.editName.text.toString()
            guardian.guardianEmail = binding.editEmail.text.toString()
            guardian.guardianPhoneNo = binding.editPhone.text.toString()
            guardian.guardianRelation = binding.editRelation.text.toString()

            db.guardianDatabaseDao.insert(guardian)

            Log.d("AddGuardian","Success")
        }
    }

}
