package com.shubham.womensafety.guardiandetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shubham.womensafety.database.Guardian
import com.shubham.womensafety.database.GuardianDatabase
import kotlinx.coroutines.*

class GuardianInfoViewModel(
    application: Application): AndroidViewModel(application) {

    private val db:GuardianDatabase = GuardianDatabase.getInstance(application)
    internal val allGuardians : LiveData<List<Guardian>> = db.guardianDatabaseDao().getAllGuardians()


    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var _showSnackbarEvent = MutableLiveData<Boolean?>()
    val showSnackBarEvent: LiveData<Boolean?>
        get() = _showSnackbarEvent

    fun insert(guardian: Guardian){
        uiScope.launch {
            withContext(Dispatchers.IO) {
                db.guardianDatabaseDao().insert(guardian)
            }
        }
    }

    fun onClear() {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                // Clear the database table.
                db.guardianDatabaseDao().clear()
            }
            // Show a snackbar message, because it's friendly.
            _showSnackbarEvent.value = true
        }
    }

    fun doneShowingSnackbar() {
        _showSnackbarEvent.value = null
    }

    //Called when ViewModel is dismantled
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
