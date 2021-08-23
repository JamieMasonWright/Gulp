package com.example.gulp.domain

import com.example.gulp.data.model.DrinkList
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    @GET("search.php")
    suspend fun getDrinkByName(@Query("s") drinkName:String): DrinkList

    @GET("filter.php")
    suspend fun getAlcoholicDrink(@Query("a") alcoholicOrNot:String): DrinkList
}