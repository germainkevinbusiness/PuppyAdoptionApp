package com.kevincodes.puppyadoptionapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kevincodes.puppyadoptionapp.ext.PuppyRow
import com.kevincodes.puppyadoptionapp.data.Puppy
import com.kevincodes.puppyadoptionapp.ext.DataSets
import com.kevincodes.puppyadoptionapp.ext.PuppyList
import com.kevincodes.puppyadoptionapp.ui.theme.PuppyAdoptionAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                val mList = DataSets.createPuppyList()
                PuppyList(mList, this@MainActivity)
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

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        PuppyAdoptionAppTheme {
            val mList = DataSets.createPuppyList()
            PuppyList(mList, this@MainActivity)
        }
    }
}