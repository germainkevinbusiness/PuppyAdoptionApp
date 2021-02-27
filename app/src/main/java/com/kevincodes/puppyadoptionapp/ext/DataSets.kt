package com.kevincodes.puppyadoptionapp.ext

import com.kevincodes.puppyadoptionapp.R
import com.kevincodes.puppyadoptionapp.data.Puppy

object DataSets {

    fun createPuppyList(): List<Puppy> {
        val puppyList = mutableListOf<Puppy>()
        puppyList.apply {
            add(
                Puppy(
                    R.drawable.pauline_loroy_unsplash,
                    "Kinzo",
                    "Female",
                    true,
                    "Likes to party",
                    200.56F
                )
            )
            add(
                Puppy(
                    R.drawable.karsten_winegeart_unsplash,
                    "Milou",
                    "Male",
                    false,
                    "Tickling every now and then",
                    230.67F
                )
            )
            add(
                Puppy(
                    R.drawable.karsten_winegeart_1_unsplash,
                    "Medor",
                    "Male",
                    false,
                    "Very affectionate",
                    190.77F
                )
            )
        }
        return puppyList
    }
}