package com.example.geneticcalc.ui.stateholder.viewModels


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.geneticcalc.data.database.entity.RelativesEntity
import com.example.geneticcalc.data.repositories.RelativesRepository


class RelativesListViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = RelativesRepository(application)
    var listLiveData: LiveData<List<RelativesEntity>> = repository.relativesList()
    fun addRelative() {
        repository.addRelative()
    }
}
