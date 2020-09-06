package com.poilkar.nehank.callreport.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.poilkar.nehank.callreport.model.CallLogModel


@Database(
    entities = [CallLogModel::class],
    version = 1
)
abstract class AppDatabase  : RoomDatabase(){

    abstract fun getAppDao() : AppDao

    companion object{

        @Volatile
        private var instance : AppDatabase? =null
        private val LOCK = Any()

        operator fun invoke (context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also{
                instance = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                AppDatabase::class.java,
                "calls_db.db")
                .fallbackToDestructiveMigration()
                .build()



    }
}