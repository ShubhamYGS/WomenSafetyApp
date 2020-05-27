package com.shubham.womensafety.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GuardianDao{
    @Insert
    fun insert(night: Guardian)

    @Query("DELETE from guardian_table")
    fun clear()

    @Query("SELECT * from guardian_table ORDER BY guardianId ASC")
    fun getAllGuardians(): List<Guardian>

}