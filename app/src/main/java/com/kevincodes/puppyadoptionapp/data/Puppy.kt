package com.kevincodes.puppyadoptionapp.data

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import com.kevincodes.puppyadoptionapp.R
import kotlinx.parcelize.Parcelize


@Parcelize
@Immutable
data class Puppy(
    val id: Int,
    @DrawableRes val image: Int,
    val name: String,
    val gender: String,
    val isAdult: Boolean,
    val qualities: String,
    val adoptionPrice: Float
) : Parcelable

object PuppiesRepo {
    fun getPuppies(): List<Puppy> = puppies
}

private val puppy1 = Puppy(
    0,
    R.drawable.pauline_loroy_unsplash,
    "Kinzo",
    "Female",
    true,
    "Likes to party",
    200.56F
)

private val puppy2 = Puppy(
    1,
    R.drawable.karsten_winegeart_unsplash,
    "Milou",
    "Male",
    false,
    "Tickling every now and then",
    230.67F
)

private val puppy3 = Puppy(
    2,
    R.drawable.karsten_winegeart_1_unsplash,
    "Medor",
    "Male",
    false,
    "Very affectionate",
    190.77F
)

private val puppy4 = Puppy(
    3,
    R.drawable.erin_minuskin_unsplash,
    "Doggy",
    "Female",
    false,
    "Very playful",
    205.00F
)

private val puppies: List<Puppy> = listOf(puppy1, puppy2, puppy3, puppy4)