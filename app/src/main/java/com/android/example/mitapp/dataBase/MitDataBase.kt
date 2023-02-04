package com.android.example.mitapp.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.example.mitapp.model.Pay
import com.android.example.mitapp.model.Card
import com.android.example.mitapp.model.User

@Database(entities = [User::class, Card::class, Pay::class], version = 1, exportSchema = false)
abstract class MitDataBase : RoomDatabase() {

    abstract val mitDao: MitDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: MitDataBase? = null

        fun getInstance(context: Context): MitDataBase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MitDataBase::class.java,
                        "Mit_history_database"
                    )
                        .fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }


}