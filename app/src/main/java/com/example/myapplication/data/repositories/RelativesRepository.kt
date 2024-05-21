package com.example.geneticcalc.data.repositories


import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import com.example.geneticcalc.data.API.RetrofitFactory
import com.example.geneticcalc.data.API.TypeCodeAPI
import com.example.geneticcalc.data.database.dao.RelativesProfilesDao
import com.example.geneticcalc.data.database.entity.RelativesEntity
//import com.example.geneticcalc.data.datasourse.RelativesDataSource
import com.example.geneticcalc.data.models.PlaceholderPost
import com.example.geneticcalc.data.protocols.RelativesProtocol
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


class RelativesRepository(private val relativesDao: RelativesProfilesDao)
{
    val allRelatives: LiveData<List<RelativesEntity>> = relativesDao.relativesList()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertRelative(newrelative: RelativesEntity){
        coroutineScope.launch (Dispatchers.IO){
            relativesDao.insert(newrelative)
        }
    }

    fun updateRelative(relative: RelativesEntity){
        coroutineScope.launch(Dispatchers.IO) {
            relativesDao.update(relative)
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