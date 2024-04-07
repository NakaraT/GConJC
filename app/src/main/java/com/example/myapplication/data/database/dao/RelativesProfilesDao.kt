package com.example.geneticcalc.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.geneticcalc.data.database.entity.RelativesEntity


@Dao
interface RelativesProfilesDao {
    @Query("SELECT * FROM relatives_profiles_table")
    fun relativesList(): LiveData<List<RelativesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(relativesEntity: RelativesEntity)

    @Query("DELETE FROM relatives_profiles_table WHERE :id = id")
    fun delete(id: Int)

    @Query("SELECT * FROM relatives_profiles_table WHERE :id = id")
    fun getItem(id: Int): LiveData<RelativesEntity>

    @Query("UPDATE relatives_profiles_table SET relativesType = :relativesType, eyeColor = :eyeColor, hairColor = :hairColor, dateofBirth = :dateofBirth, bloodType = :bloodType WHERE id = :id")
    fun update(
        id: Int,
        relativesType: String?,
        eyeColor: String?,
        hairColor: String?,
        dateofBirth: String?,
        bloodType: String?
    )
}