package com.logic.app.roomdatabase.database

import androidx.room.*

@Dao
interface UserDAO {


    @get:Query("select * from User")
    val allUser:  List <User>

    @Insert
    fun insertUser(vararg userList:  User )

    @Update
    fun updateUser(user: User?)

    @Delete
    fun deleteUser(user: User?)


}