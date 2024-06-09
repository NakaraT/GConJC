package com.example.geneticcalc.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "relatives")
data class RelativesEntity(

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    var relativeName: String = "",
    var eyeColor: String = "",
    var hairColor: String = "",
    var dateofBirth: String = "",
    var bloodType: String = ""
)

