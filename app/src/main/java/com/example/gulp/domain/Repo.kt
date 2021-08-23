package com.example.gulp.domain

import com.example.gulp.data.model.Drink
import com.example.gulp.vo.Resource

interface Repo {
    suspend fun getDrinkList(drinkName:String): Resource<List<Drink>>
    suspend fun getAlcoholicDrinks(alcoholic: String?): Resource<List<Drink>>
}