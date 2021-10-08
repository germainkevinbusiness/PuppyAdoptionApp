package com.kevincodes.puppyadoptionapp


import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kevincodes.puppyadoptionapp.data.PuppiesRepo
import com.kevincodes.puppyadoptionapp.ui.details.DetailScreen
import com.kevincodes.puppyadoptionapp.ui.home.HomeScreen
import com.kevincodes.puppyadoptionapp.utils.Utils

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
    val utils by lazy { Utils }

//    val puppies = produceState(initialValue = emptyList<Puppy>()) {
//        PuppiesRepo.getPuppies()
//    }
    val puppies by lazy { PuppiesRepo.getPuppies() }
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