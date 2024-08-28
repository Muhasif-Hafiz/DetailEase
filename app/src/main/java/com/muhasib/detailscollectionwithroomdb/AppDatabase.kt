package com.muhasib.detailscollectionwithroomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(version = 1, entities = [Person::class])
abstract class AppDatabase : RoomDatabase(), PersonDao {

    abstract fun personDao() : PersonDao

    companion object{
        @Volatile
        private var INSTANCE : AppDatabase?=null
        fun getDatabase(context : Context) : AppDatabase{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,AppDatabase::class.java, "person_database").build()

                INSTANCE=instance
                return instance
            }
        }

    }
}