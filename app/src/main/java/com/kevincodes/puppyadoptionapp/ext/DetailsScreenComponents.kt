package com.kevincodes.puppyadoptionapp.ext

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kevincodes.puppyadoptionapp.R
import com.kevincodes.puppyadoptionapp.data.Puppy
import com.kevincodes.puppyadoptionapp.ui.theme.PuppyAdoptionAppTheme
import com.kevincodes.puppyadoptionapp.ui.theme.Purple200

@Composable
fun DetailsScreen(puppy: Puppy) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Column {
            val typography = MaterialTheme.typography
            Image(
                painter = painterResource(id = puppy.image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
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
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    Text(
                        text = puppy.qualities,
                        color = Color.Gray,
                        style = typography.body2
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    Button(onClick = { }) {
                        Text(text = "Adopt now !")
                    }
                }
            }
        }
    }
}

@Preview("Details Screen")
@Composable
fun DetailsScreenPreview() {
    PuppyAdoptionAppTheme {
        val mData = Puppy(
            R.drawable.pauline_loroy_unsplash,
            "Kinzo",
            "Female",
            true,
            "Likes to party",
            200.56F
        )
        DetailsScreen(puppy = mData)
    }
}