package com.shubham.womensafety.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Guardian::class],version = 1,exportSchema = false)
abstract class GuardianDatabase: RoomDatabase() {
    abstract val guardianDatabaseDao:GuardianDao

    companion object{
        @Volatile
        private var INSTANCE: GuardianDatabase?= null

        fun getInstance(context: Context):GuardianDatabase{
            synchronized(this){
                var instance = INSTANCE

                if(instance==null){
                    instance= Room.databaseBuilder(
                        context.applicationContext,
                        GuardianDatabase::class.java,
                        "guardian_history_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE=instance
                }
                return instance
            }
        }
    }
}