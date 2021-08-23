package com.example.gulp.data

import com.example.gulp.data.model.Drink
import com.example.gulp.vo.Resource
import com.example.gulp.vo.RetrofitClient

class DataSource{

    suspend fun getDrinkByName(drinkName:String):Resource<List<Drink>>{
        return Resource.Success(RetrofitClient.webservice.getDrinkByName(drinkName).drinksList)
    }

    suspend fun getAlcoholicDrinks(alcoholic: String?): Resource<List<Drink>> {
        return Resource.Success(RetrofitClient.webservice.getAlcoholicDrink(alcoholic!!).drinksList)
    }
}