package com.example.geneticcalc.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.geneticcalc.data.database.entity.RelativesEntity


@Dao
interface RelativesProfilesDao {
    @Query("SELECT * FROM relatives")
    fun relativesList(): LiveData<List<RelativesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(relativesEntity: RelativesEntity)

    @Query("DELETE FROM relatives WHERE :id = id")
    fun delete(id: Int)

    @Query("SELECT * FROM relatives WHERE :id = id")
    fun getItem(id: Int): RelativesEntity

    @Update
    fun update(relative: RelativesEntity)

    @Query("DELETE FROM relatives")
    fun deleteAll()
}