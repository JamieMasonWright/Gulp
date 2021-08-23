package com.example.gulp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Drink(
    @SerializedName("strDrink")
    val number: String = "",
    @SerializedName("strDrinkThumb")
    val image: String = "",
    @SerializedName("strInstructions")
    val description: String = "",
    @SerializedName("strAlcoholic")
    val hasAlcohol:String = "Non_Alcoholic"
):Parcelable


data class DrinkList(
    @SerializedName("drinks")
    val drinksList:List<Drink> = listOf()
)
