package com.example.myapplication.ui.components

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.utils.Constants
import com.example.myapplication.utils.PREFERENCES
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val sharedPreferences = getSharedPreferences(PREFERENCES, MODE_PRIVATE)
            val navController = rememberNavController()
            MyApplicationTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Scaffold(
                        bottomBar = {
                            BottomNavigationBar(navController = navController)
                        },
                        content = { padding ->
                            NavHostContainer(
                                navController = navController, padding = padding, sharedPreferences = sharedPreferences
                            )
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun NavHostContainer(
    navController: NavHostController,
    padding: PaddingValues,
    sharedPreferences : SharedPreferences,
) {

    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = Modifier.padding(paddingValues = padding),
        builder = {
            composable("home") {
                    HomeScreen(navController = navController, sharedPreferences = sharedPreferences)
            }
            composable("information") {
                    InformationScreen(navController)
            }
            composable("dashboard") {
                    DashboardScreen(navController)
            }
            composable("news") {
                NewsScreen(sharedPreferences = sharedPreferences)
            }
            composable("eyes") {
                EyesScreen()
            }
            composable("hair") {
                HairScreen()
            }
            composable("blood") {
                BloodScreen()
            }
            composable("hand") {
                HandScreen()
            }
        }
    )
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colorScheme.tertiary
    ){
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        Constants.BottomNavItems.forEach { navItem ->
            BottomNavigationItem(
                selected = currentRoute == navItem.route,
                onClick = {
                    navController.navigate(navItem.route)
                },
                icon = {
                    Icon(imageVector = navItem.icon, contentDescription = navItem.label)
                },
                label = {
                    Text(text = navItem.label, color = MaterialTheme.colorScheme.secondary)
                },
                alwaysShowLabel = false
            )
        }
    }
}