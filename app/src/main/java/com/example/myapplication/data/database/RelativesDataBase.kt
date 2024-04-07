package com.example.geneticcalc.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase

import com.example.geneticcalc.data.database.dao.RelativesProfilesDao
import com.example.geneticcalc.data.database.entity.RelativesEntity


@Database(entities = [RelativesEntity::class], version = 1, exportSchema = false)
abstract class RelativesDataBase : RoomDatabase() {
    abstract fun RelativesProfilesDao(): RelativesProfilesDao

    companion object {
        @Volatile
        private var INSTANCE: RelativesDataBase? = null

        fun getDatabase(context: Context): RelativesDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RelativesDataBase::class.java,
                    "relatives_profile_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
