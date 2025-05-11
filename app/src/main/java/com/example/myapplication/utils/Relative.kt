package com.example.myapplication.utils

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

data class Relative(
    var name: String,
    var hairColor: String,
    var date: String,
    var id: Int,
    var eyeColor: String,
    var bloodType: String
)
