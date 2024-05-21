package com.example.geneticcalc.ui.stateholder.viewModels


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.geneticcalc.data.database.RelativesDataBase
import com.example.geneticcalc.data.database.entity.RelativesEntity
import com.example.geneticcalc.data.repositories.RelativesRepository


class RelativesListViewModel(application: Application) : ViewModel() {
    val allRelatives: LiveData<List<RelativesEntity>>
    private val repository : RelativesRepository
    init {
        val relativesDb = RelativesDataBase.getDatabase(application)
        val relativesDao = relativesDb.relativesDao()
        repository = RelativesRepository(relativesDao)
        allRelatives = repository.allRelatives
    }
    fun addRelative(relative: RelativesEntity){
        repository.insertRelative(relative)
    }

    fun deleteRelative(relativeId: Int){
        repository.deleteRelative(relativeId)
    }

    fun updateRelative(relative: RelativesEntity){
        repository.updateRelative(relative)
    }

    fun findRelative(relativeId: Int){
        repository.findRelative(relativeId)
    }

    fun clearRelatives(){
        repository.deleteAll()
    }
}
