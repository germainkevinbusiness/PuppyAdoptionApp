package com.kevincodes.puppyadoptionapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.withRunningRecomposer
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.withCreated
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.kevincodes.puppyadoptionapp.data.Puppy
import com.kevincodes.puppyadoptionapp.ext.*
import com.kevincodes.puppyadoptionapp.ui.theme.PuppyAdoptionAppTheme
import kotlinx.coroutines.*
import kotlin.coroutines.suspendCoroutine

@ExperimentalAnimationApi
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                HomeScreenContent()
            }
        }
    }

    @Composable
    fun MyApp(content: @Composable () -> Unit) {
        PuppyAdoptionAppTheme {
            // A surface container using the 'background' color from the theme
            Surface(color = MaterialTheme.colors.background) {
                content()
            }
        }
    }

    @Composable
    fun HomeScreenContent() {
        val navController = rememberNavController()
        val coroutineScope = rememberCoroutineScope()
        NavHost(navController = navController, startDestination = "home") {
            composable("home") {
                Column {
                    Header(false, title = "Adoption Center :)", navController)
                    PuppyList(
                        puppies = DataSets.createPuppyList(),
                        navController = navController
                    )
                }
            }

            composable(
                "details/{puppyId}",
                arguments = listOf(navArgument("puppyId") { type = NavType.IntType })
            ) {
                Column {
                    Header(true, title = "Details", navController)
                    DetailsScreen(puppyId = it.arguments?.getInt("puppyId"))
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        PuppyAdoptionAppTheme {
            HomeScreenContent()
        }
    }

    @ExperimentalAnimationApi
    @Preview("Header preview")
    @Composable
    fun HeaderPreview() {
        PuppyAdoptionAppTheme {
            val navController = rememberNavController()
            Header(true, "Test title", navController)
        }
    }
}