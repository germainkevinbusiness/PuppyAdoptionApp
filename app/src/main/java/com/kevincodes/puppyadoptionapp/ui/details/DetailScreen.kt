package com.kevincodes.puppyadoptionapp.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.kevincodes.puppyadoptionapp.MainActivity
import com.kevincodes.puppyadoptionapp.MainNavigationActions
import com.kevincodes.puppyadoptionapp.R
import com.kevincodes.puppyadoptionapp.data.Puppy
import com.kevincodes.puppyadoptionapp.ui.theme.Dark0
import com.kevincodes.puppyadoptionapp.ui.theme.Purple200
import com.kevincodes.puppyadoptionapp.ui.theme.Purple500
import com.kevincodes.puppyadoptionapp.ui.theme.Teal200
import com.kevincodes.puppyadoptionapp.utils.Utils

@Composable
fun DetailScreen(
    mainActivity: MainActivity,
    mainNavigationActions: MainNavigationActions,
    scaffoldState: ScaffoldState,
    utils: Utils
) {
    val puppy =
        mainNavigationActions.navHostController.previousBackStackEntry?.savedStateHandle?.get<Puppy>(
            "puppyData"
        )
    val isAdultOrNot = puppy?.let { if (it.isAdult) "Dog" else "Puppy" } ?: "Unspecified"
    val horizontal8dpPad = Modifier.padding(horizontal = 8.dp)
    val dividerDot = "  •  "
    val typography = MaterialTheme.typography
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.primary,
                title = {
                    Text(text = stringResource(R.string.app_details))
                },
                navigationIcon = {
                    IconButton(onClick = { mainNavigationActions.upPress() }) {
                        Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription = "")
                    }
                })
        }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(MaterialTheme.colors.background)
        ) {
            puppy?.let {
                Image(
                    painter = painterResource(id = it.image),
                    contentDescription = "My content description",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(Modifier.height(16.dp))
                Text(
                    text = it.qualities,
                    color = MaterialTheme.colors.onSurface,
                    modifier = Modifier.padding(
                        start = 8.dp,
                        top = 8.dp,
                        end = 8.dp,
                        bottom = 0.dp
                    ),
                    style = typography.body1
                )
                Spacer(Modifier.height(16.dp))
                Text(
                    text = it.name,
                    color = MaterialTheme.colors.onSurface,
                    modifier = horizontal8dpPad,
                    style = typography.body1
                )
                Spacer(Modifier.height(16.dp))
                val groupOfText = buildAnnotatedString {
                    val priceColor = if (utils.isNightModeTheme(mainActivity)) Teal200 else Dark0
                    withStyle(SpanStyle(color = priceColor)) {
                        append("${it.adoptionPrice} USD")
                    }
                    withStyle(SpanStyle(color = MaterialTheme.colors.onSurface)) {
                        append(dividerDot)
                    }
                    append(it.gender)
                    withStyle(SpanStyle(color = MaterialTheme.colors.onSurface)) {
                        append(dividerDot)
                    }
                    append(isAdultOrNot)
                }
                Text(
                    text = groupOfText,
                    modifier = horizontal8dpPad,
                    color = MaterialTheme.colors.secondary
                )
                Spacer(Modifier.height(16.dp))
                val buttonBgColor =
                    if (utils.isNightModeTheme(mainActivity)) Purple500 else Teal200
                val buttonContentColor =
                    if (utils.isNightModeTheme(mainActivity)) Color.White else Dark0
                Button(
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = buttonBgColor,
                        contentColor = buttonContentColor
                    ),
                    onClick = {
                        utils.showToast(
                            context = mainActivity,
                            message = "\uD83C\uDF89 Congratulations, you successfully adopted ${it.name} !!! \uD83C\uDF89"
                        )
                    },
                    modifier = horizontal8dpPad
                ) {
                    Text(text = "Adopt now !")
                }
                Spacer(Modifier.height(16.dp))
            }
        }
    }
}