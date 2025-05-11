package com.example.geneticcalc.data.repositories


import androidx.lifecycle.LiveData
import com.example.geneticcalc.data.database.dao.RelativesProfilesDao
import com.example.geneticcalc.data.database.entity.RelativesEntity
import com.example.myapplication.data.repositories.IRealtivesRepository
//import com.example.geneticcalc.data.datasourse.RelativesDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class RelativesRepository(private val relativesDao: RelativesProfilesDao): IRealtivesRepository
{

    override suspend fun insertRelative(newrelative: RelativesEntity){
            relativesDao.insert(newrelative)
    }

    override suspend fun updateRelative(newName: String, newEye: String, newHair: String, newDate: String, newBlood: String, id: Int){
            relativesDao.update(newName, newEye, newHair, newDate, newBlood, id)

    }

    override suspend fun deleteRelative(relativeId: Int) {
            relativesDao.delete(relativeId)

    }

    override suspend fun findRelative(relativeId: Int){
            relativesDao.getItem(relativeId)

    }

    override suspend fun deleteAll(){
            relativesDao.deleteAll()

    }

    override suspend fun getAll(): List<RelativesEntity> {
        return relativesDao.getAll()
    }
}

