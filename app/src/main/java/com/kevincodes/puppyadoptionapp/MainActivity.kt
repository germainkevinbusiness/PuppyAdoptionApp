package com.kevincodes.puppyadoptionapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.kevincodes.puppyadoptionapp.ui.theme.PuppyAdoptionAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PuppyAdoptionAppTheme {
                PuppyAdoptionGraph(mainActivity = this)
            }
        }
    }

    @Preview("Home", showBackground = true)
    @Composable
    fun DefaultPreview() {
        PuppyAdoptionAppTheme {
            PuppyAdoptionGraph(mainActivity = this)
        }
    }
}