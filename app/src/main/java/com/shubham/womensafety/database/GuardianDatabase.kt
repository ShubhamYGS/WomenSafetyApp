package com.shubham.womensafety.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Guardian::class],version = 1,exportSchema = false)
abstract class GuardianDatabase: RoomDatabase() {
    abstract fun guardianDatabaseDao():GuardianDao

    companion object{
        private var INSTANCE: GuardianDatabase?= null

        fun getInstance(context: Context):GuardianDatabase{
                if(INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(
                        context.applicationContext,
                        GuardianDatabase::class.java,
                        "GuardianDB"
                    ).build()
                }
                return INSTANCE as GuardianDatabase
        }
    }
}