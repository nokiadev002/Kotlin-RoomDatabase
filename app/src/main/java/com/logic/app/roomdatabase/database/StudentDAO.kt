package com.logic.app.roomdatabase.database

import androidx.room.*


@Dao
interface StudentDAO {

    @get:Query("select * from student")
    val allStudent:  List<Student>

    @Insert
    fun insertStudent(vararg studentList:  Student )

    @Update
    fun updateStudent(student: Student?)

    @Delete
    fun deleteStudent(student: Student?)

}