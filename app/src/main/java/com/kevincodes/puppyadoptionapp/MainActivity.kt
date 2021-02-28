package com.kevincodes.puppyadoptionapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.kevincodes.puppyadoptionapp.data.PuppiesRepo
import com.kevincodes.puppyadoptionapp.ui.components.*
import com.kevincodes.puppyadoptionapp.ui.theme.PuppyAdoptionAppTheme

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
    private fun MyApp(content: @Composable () -> Unit) {
        MaterialTheme {
            // A surface container using the 'background' color from the theme
            Surface(color = MaterialTheme.colors.background) {
                content()
            }
        }
    }

    @Composable
    fun HomeScreenContent() {
        val navController = rememberNavController()
        val puppies = remember { PuppiesRepo.getPuppies() }
        NavHost(navController = navController, startDestination = "home") {
            composable("home") {
                Scaffold(topBar = {
                    AppBar(
                        false,
                        stringResource(R.string.app_name), navController
                    )
                }) { innerPadding ->
                    LazyColumn(contentPadding = innerPadding) {
                        items(puppies) { puppy ->
                            PuppyRow(puppy = puppy) {
                                navController.navigate("details/${puppy.id}")
                            }
                        }
                    }
                }
            }

            composable(
                "details/{puppyId}",
                arguments = listOf(navArgument("puppyId") { type = NavType.IntType })
            ) { backStackEntry ->
                Scaffold(topBar = {
                    AppBar(
                        true,
                        stringResource(R.string.app_details), navController
                    )
                }) { innerPadding ->
                    LazyColumn(contentPadding = innerPadding) {
                        item {
                            DetailsScreen(puppyId = backStackEntry.arguments?.getInt("puppyId"))
                        }
                    }
                }
            }
        }
    }

    @Preview("Home", showBackground = true)
    @Composable
    fun DefaultPreview() {
        MyApp {
            HomeScreenContent()
        }
    }

    @Preview("Details")
    @Composable
    fun DetailsScreenPreview() {
        PuppyAdoptionAppTheme {
            DetailsScreen(0)
        }
    }
}