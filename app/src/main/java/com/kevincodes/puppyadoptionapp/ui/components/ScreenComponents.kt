package com.kevincodes.puppyadoptionapp.ui.components


import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.kevincodes.puppyadoptionapp.R
import com.kevincodes.puppyadoptionapp.data.PuppiesRepo
import com.kevincodes.puppyadoptionapp.data.Puppy
import com.kevincodes.puppyadoptionapp.ui.theme.Purple200
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun AppBar(shouldBackButtonBeVisible: Boolean, title: String, navController: NavHostController) {
    TopAppBar(
        navigationIcon = {
            Image(
                painter = painterResource(
                    id =
                    if (shouldBackButtonBeVisible) R.drawable.ic_baseline_arrow_back_ios_24
                    else R.drawable.ic_baseline_pets_24
                ),
                contentDescription = null,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = true)
                    ) {
                        if (shouldBackButtonBeVisible) navController.popBackStack()
                    }
            )
        },
        title = {
            Text(text = title)
        },
        backgroundColor = MaterialTheme.colors.primary
    )
}

@Composable
fun PuppyRow(puppy: Puppy, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
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
                .background(Color.White)
                .padding(8.dp)
                .fillMaxWidth(),
        ) {
            Text(
                text = puppy.qualities,
                color = Color.Gray,
                style = typography.body2
            )
            Spacer(Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                val isAdultOrNot = if (puppy.isAdult) "Dog" else "Puppy"
                Text(
                    text = puppy.name,
                    color = Color.Black,
                    style = typography.body2
                )
                Text(text = dividerDot, color = Color.Black)
                Text(
                    text = " ${puppy.gender} ",
                    color = Purple200,
                    style = typography.body2
                )
                Text(text = dividerDot, color = Color.Black)
                Text(
                    text = isAdultOrNot,
                    color = Purple200,
                    style = typography.body2
                )
            }
            Spacer(Modifier.height(8.dp))
        }
    }
}

@Composable
fun DetailsScreen(puppyId: Int?) {
    puppyId?.let {
        val listOfPuppies = PuppiesRepo.getPuppies()
        val typography = MaterialTheme.typography
        val padding8dp = Modifier.padding(horizontal = 8.dp)
        val dividerDot = "  •  "
        listOfPuppies.forEach { puppy ->
            if (puppy.id == it) {
                val isAdultOrNot = if (puppy.isAdult) "Dog" else "Puppy"
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(Color.White)
                ) {
                    CoilImage(
                        data = puppy.image,
                        contentDescription = "My content description",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(Modifier.height(16.dp))
                    Text(
                        text = puppy.qualities,
                        color = Color.Gray,
                        modifier = Modifier.padding(
                            start = 8.dp,
                            top = 8.dp,
                            end = 8.dp,
                            bottom = 0.dp
                        ),
                        style = typography.body2
                    )
                    Spacer(Modifier.height(16.dp))
                    Text(
                        text = puppy.name,
                        color = Color.Black,
                        modifier = padding8dp,
                        style = typography.body1
                    )
                    Spacer(Modifier.height(16.dp))
                    val groupOfText = buildAnnotatedString {
                        append("${puppy.adoptionPrice} USD")
                        append(dividerDot)
                        append(puppy.gender)
                        append(dividerDot)
                        append(isAdultOrNot)
                    }
                    Text(
                        text = groupOfText,
                        modifier = padding8dp
                    )
                    Spacer(Modifier.height(16.dp))
                    val context: Context = LocalContext.current
                    Button(
                        onClick = {
                            Toast.makeText(
                                context,
                                "\uD83C\uDF89 Congratulations, you successfully adopted ${puppy.name} !!! \uD83C\uDF89",
                                Toast.LENGTH_LONG
                            ).show()
                        },
                        modifier = padding8dp
                    ) {
                        Text(text = "Adopt now !")
                    }
                    Spacer(Modifier.height(16.dp))
                }
            }
        }
    }
}