package com.example.myapplication.utils

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.geneticcalc.data.database.entity.RelativesEntity


@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
inline fun <reified VM : ViewModel> sharedViewModel(
    navController: NavController,
): VM {
    val parentEntry = remember { navController.getBackStackEntry("home") }
    return hiltViewModel(parentEntry)
}

inline fun Relative.toRelativeEntity(): RelativesEntity{
    return RelativesEntity(0, this.name, this.eyeColor, this.hairColor, this.date, this.bloodType)
}

inline fun RelativesEntity.toRelative(): Relative{
    return Relative(this.relativeName, this.hairColor, this.dateofBirth, this.id, this.eyeColor, this.bloodType)
}