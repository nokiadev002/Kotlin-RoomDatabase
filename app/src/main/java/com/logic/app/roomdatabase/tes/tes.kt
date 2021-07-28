//package com.logic.app.roomdatabase.tes
//
//import android.content.Context
//import android.provider.ContactsContract.CommonDataKinds.Note
//import android.provider.SyncStateContract.Constants
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//
//
//
//
//
//
//
//
//
//
//
//
//
//    @Database(entities = [Note::class], version = 1)
//    abstract class tes : RoomDatabase() {
//        abstract val noteDao: NoteDao?
//
//        fun cleanUp() {
//            noteDB = null
//        }
//
//        companion object {
//            private var noteDB: tes? = null
//            fun getInstance(context: Context): tes? {
//                if (null == noteDB) {
//                    noteDB = buildDatabaseInstance(context)
//                }
//                return noteDB
//            }
//
//            private fun buildDatabaseInstance(context: Context): tes {
//                return Room.databaseBuilder(
//                    context,
//                    tes::class.java,
//                    Constants.DB_NAME
//                )
//                    .allowMainThreadQueries().build()
//            }
//        }
//    }
//
//
//
//
//
//
//
//
//
//
////companion object {
////    @Volatile private var instance: AppDatabase? = null
////    private val LOCK = Any()
////
////    operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
////        instance ?: buildDatabase(context).also { instance = it}
////    }
////
////    private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
////        AppDatabase::class.java, "todo-list.db")
////        .build()
//}
