package com.example.geneticcalc.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase

import com.example.geneticcalc.data.database.dao.RelativesProfilesDao
import com.example.geneticcalc.data.database.entity.RelativesEntity


@Database (entities = [RelativesEntity::class], version = 1, exportSchema = false)
abstract class RelativesDataBase : RoomDatabase() {
    abstract fun relativesDao(): RelativesProfilesDao

    companion object {

        private var Instance: RelativesDataBase? = null

        fun getDatabase(context: Context): RelativesDataBase {
            synchronized(this) {
                var instance = Instance
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RelativesDataBase::class.java,
                        "relatives"
                    ).fallbackToDestructiveMigration()
                        .build()
                    Instance = instance
                }
                return instance
            }
        }
    }
}

