package com.example.myapplication.data.repositories

import com.example.geneticcalc.data.database.entity.RelativesEntity

interface IRealtivesRepository {
    suspend fun insertRelative(newrelative: RelativesEntity)
    suspend fun updateRelative(newName: String, newEye: String, newHair: String, newDate: String, newBlood: String, id: Int)
    suspend fun deleteRelative(relativeId: Int)
    suspend fun findRelative(relativeId: Int)
    suspend fun deleteAll()
    suspend fun getAll(): List<RelativesEntity>
}
