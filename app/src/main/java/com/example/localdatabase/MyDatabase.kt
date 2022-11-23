package com.example.localdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized
@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {
    companion object{
        private var INSTANCE : MyDatabase? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getInstance(context: Context):MyDatabase?{
            if (INSTANCE == null){
                synchronized(MyDatabase::class.java){
                   INSTANCE = Room.databaseBuilder(
                       context,MyDatabase::class.java,
                       "MyDatabase"
                   ).build()

                    return INSTANCE
                }
            }

            return INSTANCE
        }
    }

   abstract fun Usereddao():UserDao
}
