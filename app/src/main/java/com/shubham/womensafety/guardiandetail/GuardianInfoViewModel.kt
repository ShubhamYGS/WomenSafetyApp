package com.shubham.womensafety.guardiandetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.shubham.womensafety.database.Guardian
import com.shubham.womensafety.database.GuardianDatabase
import kotlinx.coroutines.*

class GuardianInfoViewModel(
    application: Application): AndroidViewModel(application) {

    private val db:GuardianDatabase = GuardianDatabase.getInstance(application)
    internal val allGuardians : LiveData<List<Guardian>> = db.guardianDatabaseDao().getAllGuardians()


    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun insert(guardian: Guardian){
        uiScope.launch {
            withContext(Dispatchers.IO) {
                db.guardianDatabaseDao().insert(guardian)
            }
        }
    }
}
