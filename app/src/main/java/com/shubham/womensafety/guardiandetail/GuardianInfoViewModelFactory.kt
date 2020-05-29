package com.shubham.womensafety.guardiandetail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shubham.womensafety.database.GuardianDao
import java.lang.IllegalArgumentException


class GuardianInfoViewModelFactory(
    private val dataSource: GuardianDao,
    private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(GuardianInfoViewModel::class.java)){
            return GuardianInfoViewModel(dataSource,application) as T
        }
        throw IllegalArgumentException("Unknown Viewmodel Class")
    }
}