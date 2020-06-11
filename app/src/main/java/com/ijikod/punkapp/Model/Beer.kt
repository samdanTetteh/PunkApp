package com.ijikod.punkapp.Model

import android.os.Parcelable
import androidx.room.*
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Beers")
data class Beer (
    @PrimaryKey()
    val id: Long,
    val name: String?,
    val tagline: String?,
    @field:Json(name = "first_brewed")
    val firstBrewed: String?,
    val description: String?,
    @field:Json(name = "image_url")
    val imageURL: String?,
    val abv: Double?,
    val ibu: Double?,
    @field:Json(name = "target_fg")
    val targetFg: Double?,
    @field:Json(name = "target_og")
    val targetOg: Double?,
    val ebc: Double?,
    val srm: Double?,
    val ph: Double?,
    @field:Json(name = "attenuation_level")
    val attenuationLevel: Double?,
    @Embedded(prefix = "volume_")
    val volume: BoilVolume,
    @Embedded(prefix = "boilVolume_")
    @field:Json(name = "boil_volume")
    val boilVolume: BoilVolume?,
    @Embedded
    val method: Method?,
    @Embedded
    val ingredients: Ingredients?,
    @field:Json(name = "food_pairing")
    val foodPairings: List<String>?,
    @field:Json(name = "brewers_tips")
    val brewersTips: String?,
    @field:Json(name = "contributed_by")
    val contributedBy: String?
) : Parcelable

@Parcelize
data class BoilVolume (
    val value: Double?,
    val unit: String?
) : Parcelable


@Parcelize
data class Ingredients (
    val malt: List<Malt>?,
    val hops: List<Hop>?,
    val yeast: String?
) : Parcelable

@Parcelize
data class Hop (
    val name: String?,
    @Embedded(prefix = "hop_")
    val amount: BoilVolume?,
    val add: String?,
    val attribute: String?
) : Parcelable

@Parcelize
data class Malt (
    val name: String?,
    @Embedded(prefix = "malt_")
    val amount: BoilVolume?
) : Parcelable

@Parcelize
data class Method (
    @field:Json(name = "mash_temp")
    val mashTemp: List<MashTemp>?,
    @Embedded
    val fermentation: Fermentation?,
    val twist: String?
) : Parcelable

@Parcelize
data class Fermentation (
    @Embedded(prefix = "fermentation_")
    val temp: BoilVolume?
) : Parcelable

@Parcelize
data class MashTemp (
    @Embedded(prefix = "mash_temp_")
    val temp: BoilVolume?,
    val duration: Long?
) : Parcelable
