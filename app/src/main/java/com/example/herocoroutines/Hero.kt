package com.example.herocoroutines

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Hero(val name:String?, val work:WorkBlock, val images: ImagesBlock, val biography: Biography):
    Parcelable
@Parcelize
data class ImagesBlock(val xs:String, val sm:String, val md:String, val lg:String): Parcelable
@Parcelize
data class WorkBlock(val occupation:String, val base:String): Parcelable
@Parcelize
data class Biography(val fullName:String, val alterEgos:String, val aliases: Array<String>,
                     val placeOfBirth: String, val firstAppearance: String, val publisher: String, val alignment: String):
    Parcelable

