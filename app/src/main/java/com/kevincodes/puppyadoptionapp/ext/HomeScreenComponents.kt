package com.kevincodes.puppyadoptionapp.ext

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kevincodes.puppyadoptionapp.R
import com.kevincodes.puppyadoptionapp.data.Puppy
import com.kevincodes.puppyadoptionapp.ui.theme.PuppyAdoptionAppTheme
import com.kevincodes.puppyadoptionapp.ui.theme.Purple200

@Composable
fun Header() {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Color.White)
            .padding(start = 16.dp, end = 16.dp)
    ) {
        val typography = MaterialTheme.typography
        Image(
            painter = painterResource(
                id = R.drawable.ic_baseline_pets_24,
            ),
            contentDescription = null
        )
        Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
            Text(text = "Adoption Center :)", style = typography.h5)
        }
    }
}

@Preview("Header preview")
@Composable
fun HeaderPreview() {
    PuppyAdoptionAppTheme {
        Header()
    }
}

@Composable
fun PuppyRow(puppy: Puppy, onClick: () -> Unit) {
    Column(
        Modifier
            .fillMaxWidth()
            .clickable { onClick() }) {
        val typography = MaterialTheme.typography
        Image(
            painter = painterResource(id = puppy.image),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Crop
        )
        Column(
            Modifier
                .background(Color.White)
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                val isAdultOrNot = if (puppy.isAdult) "Dog" else "Puppy"
                Text(
                    text = puppy.name,
                    color = Color.Black,
                    style = typography.body2
                )
                Text(
                    text = " ${puppy.gender} ",
                    color = Purple200,
                    style = typography.body2
                )
                Text(
                    text = isAdultOrNot,
                    color = Purple200,
                    style = typography.body2
                )
            }
            Text(
                text = puppy.qualities,
                color = Color.Gray,
                style = typography.body2
            )
        }
    }
}

@Composable
fun PuppyList(puppies: List<Puppy>, context: Context) {
    Box(modifier = Modifier.fillMaxWidth()) {
        LazyColumn {
            items(puppies) { puppy ->
                PuppyRow(puppy = puppy) {
                    Toast.makeText(context, puppy.name, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}