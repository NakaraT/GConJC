package com.example.geneticcalc.ui.stateholder.viewModels
//
//import android.app.Application
//import androidx.lifecycle.AndroidViewModel
//import androidx.lifecycle.LiveData
//import com.example.geneticcalc.data.database.entity.RelativesEntity
//import com.example.geneticcalc.data.repositories.RelativesRepository
//
//class RelativesListItemViewModel(application: Application) : AndroidViewModel(application) {
//    private val repository = RelativesRepository(application)
//    // danger (fix)
//    lateinit var relativesListItemLiveData: LiveData<RelativesEntity>
//    fun getRelativesItem(id: Int) {
//        relativesListItemLiveData = repository.getRelativesItem(id)
//    }
//
//    fun deleteRelative(id: Int) {
//        repository.deleteRelative(id)
//    }
//
//    fun updateRelative(
//        id: Int,
//        relativesType: String?,
//        eyeColor: String?,
//        hairColor: String?,
//        dateofBirth: String?,
//        bloodType: String?
//    ) {
//        repository.updateRelative(id, relativesType, eyeColor, hairColor, dateofBirth, bloodType)
//    }
//}