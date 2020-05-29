package com.shubham.womensafety.guardiandetail

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shubham.womensafety.database.Guardian
import com.shubham.womensafety.database.GuardianDao
import kotlinx.coroutines.*

class GuardianInfoViewModel(
    dataSource: GuardianDao,
    application: Application):ViewModel() {

    val database = dataSource

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var toguardian = MutableLiveData<Guardian?>()

    val guardians = database.getAllGuardians()

    private fun initializeToguardian(){
        uiScope.launch {

        }
    }


    init {
        initializeToguardian()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}

