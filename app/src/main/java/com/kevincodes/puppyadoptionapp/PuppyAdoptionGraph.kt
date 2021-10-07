package com.kevincodes.puppyadoptionapp

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.kevincodes.puppyadoptionapp.data.PuppiesRepo
import com.kevincodes.puppyadoptionapp.data.Puppy
import com.kevincodes.puppyadoptionapp.ui.details.DetailScreen
import com.kevincodes.puppyadoptionapp.ui.home.HomeScreen
import com.kevincodes.puppyadoptionapp.utils.Utils
import kotlinx.coroutines.launch

//Navigation graph destinations
object MainDestinations {
    const val HOME_ROUTE = "home_route"
    const val DETAIL_ROUTE = "detail_route"
}

// Helper class to do navigation
class MainNavigationActions(val navHostController: NavHostController) {

    val navigateToDetail: () -> Unit = {
        navHostController.navigate(MainDestinations.DETAIL_ROUTE)
    }
    val upPress: () -> Unit = { navHostController.navigateUp() }
}

@Composable
fun PuppyAdoptionGraph(mainActivity: MainActivity) {
    val scaffoldState = rememberScaffoldState()
    val navController = rememberNavController()
    // actions you can take between composables
    val mainNavigationActions = remember(navController) {
        MainNavigationActions(navController)
    }
    val systemUiController = rememberSystemUiController()
    val utils by lazy { Utils }

//    SideEffect {
//        systemUiController.statusBarDarkContentEnabled = utils.isNightModeTheme(mainActivity)
//    }
    val puppies = PuppiesRepo.getPuppies()
    Scaffold(
        scaffoldState = scaffoldState
    ) {
        NavHost(navController = navController, startDestination = MainDestinations.HOME_ROUTE) {
            composable(route = MainDestinations.HOME_ROUTE) {
                HomeScreen(
                    puppies = puppies,
                    scaffoldState = scaffoldState,
                    mainNavigationActions = mainNavigationActions
                )
            }
            composable(route = MainDestinations.DETAIL_ROUTE) {
                DetailScreen(
                    mainNavigationActions = mainNavigationActions,
                    scaffoldState = scaffoldState,
                    mainActivity = mainActivity,
                    utils = utils
                )
            }
        }
    }
}