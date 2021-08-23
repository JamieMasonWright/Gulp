package com.example.gulp.iu.viewmodel

import androidx.lifecycle.*

import com.example.gulp.domain.Repo
import com.example.gulp.vo.Resource
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val repo: Repo):ViewModel(){

    private val drinkData = MutableLiveData<String>()
    private val alcoholicOrnotData = MutableLiveData<String>()

    fun setDrink(drinkName:String){
        drinkData.value = drinkName
    }

    fun setAlcoholicOrNotFilter(alcoholicOrNot:String){
        alcoholicOrnotData.value = alcoholicOrNot
    }

    val fetchDrinkList = drinkData.distinctUntilChanged().switchMap { drinkName ->
        liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try{
                emit(repo.getDrinkList(drinkName))
            }catch (e: Exception){

            }
        }
    }

    val fetchAlcoholicFilter = alcoholicOrnotData.distinctUntilChanged().switchMap { isAlcoholic ->
        liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try{
                emit(repo.getAlcoholicDrinks(isAlcoholic))
            }catch (e: Exception){

            }
        }
    }

}