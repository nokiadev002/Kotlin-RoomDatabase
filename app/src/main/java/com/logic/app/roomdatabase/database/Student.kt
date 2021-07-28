package com.logic.app.roomdatabase.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class Student {


    @PrimaryKey(autoGenerate = true)
    var uid = 0

    @ColumnInfo(name = "name")
    var name: String? = null

//    @ColumnInfo(name = "firstName")
//    var firstName: String? = null

    @ColumnInfo(name = "phone")
    var phone: String? = null


}



