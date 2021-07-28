package com.logic.app.roomdatabase.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Student::class, User::class], version = 1)
abstract class MyDatabase : RoomDatabase() {
    abstract fun studentDAO(): StudentDAO?
    abstract fun userDAO(): UserDAO?



//    companion object {
//        @Volatile private var instance: MyDatabase? = null
//        private val LOCK = Any()
//
//        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
//            instance ?: buildDatabase(context).also { instance = it}
//        }
//
//        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
//            MyDatabase::class.java, "todo-list.db")
//            .build()
//    }



//    companion object {
//
//        // For Singleton instantiation
//        @Volatile private var instance: MyDatabase? = null
//
//        fun getInstance(context: Context): MyDatabase {
//            return instance ?: synchronized(this) {
//                instance ?: buildDatabase(context).also { instance = it }
//            }
//        }
//
//        // Create and pre-populate the database. See this article for more details:
//        // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
//        private fun buildDatabase(context: Context): MyDatabase {
//            return Room.databaseBuilder(context, MyDatabase::class.java, DATABASE_NAME)
//                .addCallback(
//                    object : RoomDatabase.Callback() {
//                        override fun onCreate(db: SupportSQLiteDatabase) {
//                            super.onCreate(db)
//                            val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>()
//                                .setInputData(workDataOf(KEY_FILENAME to PLANT_DATA_FILENAME))
//                                .build()
//                            WorkManager.getInstance(context).enqueue(request)
//                        }
//                    }
//                )
//                .build()
//        }
//    }





}