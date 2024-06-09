package com.example.geneticcalc.data.repositories


import androidx.lifecycle.LiveData
import com.example.geneticcalc.data.database.dao.RelativesProfilesDao
import com.example.geneticcalc.data.database.entity.RelativesEntity
//import com.example.geneticcalc.data.datasourse.RelativesDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class RelativesRepository(private val relativesDao: RelativesProfilesDao)
{
    val allRelatives: LiveData<List<RelativesEntity>> = relativesDao.relativesList()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertRelative(newrelative: RelativesEntity){
        coroutineScope.launch (Dispatchers.IO){
            relativesDao.insert(newrelative)
        }
    }

    fun updateRelative(newName: String, newEye: String, newHair: String, newDate: String, newBlood: String, id: Int){
        coroutineScope.launch(Dispatchers.IO) {
            relativesDao.update(newName, newEye, newHair, newDate, newBlood, id)
        }
    }

    fun deleteRelative(relativeId: Int) {
        coroutineScope.launch(Dispatchers.IO) {
            relativesDao.delete(relativeId)
        }
    }

    fun findRelative(relativeId: Int){
        coroutineScope.launch (Dispatchers.IO){
            relativesDao.getItem(relativeId)
        }
    }

    fun deleteAll(){
        coroutineScope.launch(Dispatchers.IO) {
            relativesDao.deleteAll()
        }
    }
}

