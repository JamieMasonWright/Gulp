package com.example.gulp.domain

import com.example.gulp.data.DataSource
import com.example.gulp.data.model.Drink
import com.example.gulp.vo.Resource


class RepoImpl(private val dataSource: DataSource): Repo {

    override suspend fun getDrinkList(drinkName:String): Resource<List<Drink>> {
        return dataSource.getDrinkByName(drinkName)
    }

    override suspend fun getAlcoholicDrinks(alcoholic: String?): Resource<List<Drink>> {
        return dataSource.getAlcoholicDrinks(alcoholic)
    }
    
}