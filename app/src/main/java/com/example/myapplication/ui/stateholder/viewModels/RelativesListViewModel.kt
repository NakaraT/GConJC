package com.example.geneticcalc.ui.stateholder.viewModels


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.geneticcalc.data.database.RelativesDataBase
import com.example.geneticcalc.data.database.entity.RelativesEntity
import com.example.geneticcalc.data.repositories.RelativesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


@HiltViewModel
class RelativesListViewModel @Inject constructor(private val repository: RelativesRepository) : ViewModel() {

    private val _allRelatives = MutableStateFlow<List<RelativesEntity>>(emptyList())
    val allRelatives: StateFlow<List<RelativesEntity>> get() = _allRelatives

    val bloodTypes = arrayOf("I+", "I-", "II+", "II-", "III+", "III-", "IV+", "IV-")
    val eyeColors = arrayOf("Голубые", "Карие", "Серые", "Зелёные")
    val hairColors = arrayOf("Светлый", "Карий", "Рыжий", "Тёмный")


    fun getRelatives(){
        viewModelScope.launch(Dispatchers.IO) {
            _allRelatives.value = repository.getAll()
        }
    }

    fun addRelative(relative: RelativesEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertRelative(relative)
        }
    }

    fun deleteRelative(relativeId: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteRelative(relativeId)
        }
    }

    fun updateRelative(newName: String, newEye: String, newHair: String, newDate: String, newBlood: String, id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateRelative(newName, newEye, newHair, newDate, newBlood, id)
        }
    }

    fun findRelative(relativeId: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.findRelative(relativeId)
        }
    }

    fun clearRelatives(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }
}
