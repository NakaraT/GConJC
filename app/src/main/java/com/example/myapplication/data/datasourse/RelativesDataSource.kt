package com.example.geneticcalc.data.datasourse

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.geneticcalc.data.database.RelativesDataBase
import com.example.geneticcalc.data.database.dao.RelativesProfilesDao
import com.example.geneticcalc.data.database.entity.RelativesEntity


class RelativesDataSource(context: Context) {
    private val db: RelativesDataBase
    private val dao: RelativesProfilesDao

    init {
        db = RelativesDataBase.getDatabase(context)
        dao = db.RelativesProfilesDao()
    }

    val relativesList: LiveData<List<RelativesEntity>>
        get() = dao.relativesList()

    fun getRelativesItem(id: Int): LiveData<RelativesEntity> {
        return dao.getItem(id)
    }

    fun addRelative() {
        db.queryExecutor.execute { dao.insert(RelativesEntity()) }
    }

    fun deleteRelative(id: Int) {
        db.queryExecutor.execute { dao.delete(id) }
    }

    fun updateRelative(
        id: Int,
        relativesType: String?,
        eyeColor: String?,
        hairColor: String?,
        dateofBirth: String?,
        bloodType: String?
    ) {
        db.queryExecutor.execute {
            dao.update(
                id,
                relativesType,
                eyeColor,
                hairColor,
                dateofBirth,
                bloodType
            )
        }
    }
}