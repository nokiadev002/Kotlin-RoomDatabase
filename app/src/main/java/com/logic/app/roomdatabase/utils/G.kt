package com.logic.app.roomdatabase.utils

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.room.Room
import com.logic.app.roomdatabase.database.MyDatabase

class G : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
//        var db: MyDatabase? = null
//        fun getDatabase(): MyDatabase? {
//            return db
//        }

    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
//        db=MyDatabase.invoke(context)
//        db= Room.databaseBuilder(applicationContext, MyDatabase::class.java,"DB").allowMainThreadQueries().build()

//        db = MyDatabase(this)
    }


}