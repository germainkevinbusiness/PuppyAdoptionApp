package com.kevincodes.puppyadoptionapp.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kevincodes.puppyadoptionapp.MainNavigationActions
import com.kevincodes.puppyadoptionapp.R
import com.kevincodes.puppyadoptionapp.data.Puppy
import com.kevincodes.puppyadoptionapp.ui.theme.Purple200

@Composable
fun HomeScreen(
    puppies: List<Puppy>,
    scaffoldState: ScaffoldState,
    mainNavigationActions: MainNavigationActions
) {
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.primary,
                title = {
                    Text(text = stringResource(R.string.app_name))
                })
        }) { innerPadding ->
        LazyColumn(contentPadding = innerPadding) {
            itemsIndexed(puppies) { _: Int, item: Puppy ->
                PuppyRow(puppy = item) {
                    mainNavigationActions.navHostController.currentBackStackEntry?.savedStateHandle?.set(
                        "puppyData",
                        item
                    )
                    mainNavigationActions.navigateToDetail()
                }
            }
        }
    }
}

@Composable
fun PuppyRow(puppy: Puppy, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
            .clickable { onClick() }
    ) {
        val typography = MaterialTheme.typography
        val dividerDot = "  •  "
        Image(
            painter = painterResource(id = puppy.image),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .padding(8.dp)
                .fillMaxWidth(),
        ) {
            Text(
                text = puppy.qualities,
                color = MaterialTheme.colors.onSurface,
                style = typography.body1
            )
            Spacer(Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                val isAdultOrNot = if (puppy.isAdult) "Dog" else "Puppy"
                Text(
                    text = puppy.name,
                    color = MaterialTheme.colors.onSurface,
                    style = typography.body1
                )
                Text(text = dividerDot, color = MaterialTheme.colors.onSurface)
                Text(
                    text = " ${puppy.gender} ",
                    color = Purple200,
                    style = typography.body1
                )
                Text(text = dividerDot, color = MaterialTheme.colors.onSurface)
                Text(
                    text = isAdultOrNot,
                    color = Purple200,
                    style = typography.body1
                )
            }
            Spacer(Modifier.height(8.dp))
        }
    }
}