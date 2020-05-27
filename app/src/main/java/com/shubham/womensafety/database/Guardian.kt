package com.shubham.womensafety.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "guardian_table")
data class Guardian (
    @PrimaryKey(autoGenerate = true)
    val guardianId:Long= 0L,

    @ColumnInfo(name = "guardian_name")
    val guardianName:String="",

    @ColumnInfo(name="guardian_relation")
    var guardianRelation:String="",

    @ColumnInfo(name="guardian_phoneno")
    var guardianPhoneNo:Int = 0
)
