package com.logic.app.roomdatabase.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class User {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    @ColumnInfo(name = "address")
    var address: String? = null


    @ColumnInfo(name = "country")
    var country: String? = null

//    @ColumnInfo(name = "age")
//    var age: Int? = null


}