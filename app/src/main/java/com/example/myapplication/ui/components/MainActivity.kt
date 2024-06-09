package com.example.myapplication.ui.components

import android.app.Application
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
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.geneticcalc.ui.stateholder.viewModels.DashboardViewModel
import com.example.geneticcalc.ui.stateholder.viewModels.HomeViewModel
import com.example.geneticcalc.ui.stateholder.viewModels.RelativesListViewModel

import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.utils.Constants
import com.example.myapplication.utils.PREFERENCES

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
                                navController = navController, padding = padding, sharedPreferences = sharedPreferences, homeViewModel = HomeViewModel(),
                                relativesListViewModel =
                                LocalViewModelStoreOwner.current?.let { viewModel (it, "RelativesListViewModel",RelativeListViewModelFactory(LocalContext.current.applicationContext as Application))},
                                dashboardViewModel =
                                LocalViewModelStoreOwner.current?.let { viewModel (it, "DashboardViewModel",DashboardViewModelFactory(LocalContext.current.applicationContext as Application))}
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
    homeViewModel: HomeViewModel?,
    relativesListViewModel: RelativesListViewModel?,
    dashboardViewModel: DashboardViewModel?
) {

    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = Modifier.padding(paddingValues = padding),
        builder = {
            composable("home") {
                if (homeViewModel != null) {
                    HomeScreen(navController = navController, sharedPreferences = sharedPreferences, homeViewModel)
                }
            }
            composable("information") {
                if (relativesListViewModel != null) {
                    InformationScreen(relativesListViewModel)
                }
            }
            composable("dashboard") {
                if (dashboardViewModel != null) {
                    DashboardScreen(dashboardViewModel)
                }
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