package com.kevincodes.puppyadoptionapp.ext

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.kevincodes.puppyadoptionapp.R
import com.kevincodes.puppyadoptionapp.data.Puppy
import com.kevincodes.puppyadoptionapp.ui.theme.Purple200

@ExperimentalAnimationApi
@Composable
fun Header(shouldBackButtonBeVisible: Boolean, title: String, navController: NavHostController) {
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
        AnimatedVisibility(shouldBackButtonBeVisible) {
            Image(
                painter = painterResource(
                    id = R.drawable.ic_baseline_arrow_back_ios_24,
                ),
                contentDescription = "Go back button",
                modifier = Modifier.clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = true)
                ) { navController.popBackStack() }
            )
        }
        Image(
            painter = painterResource(
                id = R.drawable.ic_baseline_pets_24,
            ),
            contentDescription = null
        )
        Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
            Text(text = title, style = typography.h5)
        }
    }
}

@Composable
fun PuppyList(
    puppies: List<Puppy>,
    navController: NavHostController
) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(puppies) { puppy ->
            PuppyRow(puppy = puppy) {
                navController.navigate("details/${puppy.id}")
            }
        }
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