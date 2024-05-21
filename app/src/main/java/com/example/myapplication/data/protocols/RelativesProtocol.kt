package com.example.geneticcalc.data.protocols

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.geneticcalc.data.database.entity.RelativesEntity
import com.example.geneticcalc.data.models.PlaceholderPost

interface RelativesProtocol {
    fun relativesList(): LiveData<List<RelativesEntity>>

    fun getRelativesItem(position: Int): LiveData<RelativesEntity>
//    val post: LiveData<PlaceholderPost>

//    fun pushPost(): LiveData<PlaceholderPost>
//    val allPosts: MutableLiveData<List<PlaceholderPost>>

    fun addRelative()
    fun deleteRelative(id: Int)
    fun updateRelative(
        id: Int,
        relativesType: String?,
        eyeColor: String?,
        hairColor: String?,
        dateofBirth: String?,
        bloodType: String?
    )
}