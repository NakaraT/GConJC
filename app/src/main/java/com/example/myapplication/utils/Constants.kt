package com.example.myapplication.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import com.example.myapplication.models.BottomNavItem


const val PREFERENCES = "NakaraT_Preference"
const val EDIT_KEY = "dark_theme"
const val SEARCH_KEY = "query"
object Constants {
    val BottomNavItems = listOf(
        BottomNavItem(
            label = "Главная",
            icon = Icons.Filled.Home,
            route = "home"
        ),
        BottomNavItem(
            label = "Информация",
            icon = Icons.Filled.Person,
            route = "information"
        ),
        BottomNavItem(
            label = "Таблица",
            icon = Icons.Filled.Menu,
            route = "dashboard"
        )
    )
}