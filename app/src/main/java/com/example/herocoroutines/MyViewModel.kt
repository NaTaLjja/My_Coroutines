package com.example.herocoroutines

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(val repo: Repository):ViewModel() {

    private val _uiState = MutableLiveData<UiState>(UiState.Empty)
    val uiState:LiveData<UiState> = _uiState
   // val repo = MyApplication.getApp().repo

    fun getData(){
        viewModelScope.launch (Dispatchers.IO){
            try{
                val heroResponse = repo.getListHeroes()
                if(heroResponse.isSuccessful){

                    val data  = heroResponse.body()
                    _uiState.postValue(UiState.Result(data))
                }else{
                    _uiState.postValue(UiState.Error("Error Code ${heroResponse.code()}"))
                }
            }catch (e: Exception){
                _uiState.postValue(UiState.Error(e.localizedMessage))

            }


        }
    }

    sealed class UiState{
        object Empty:UiState()
        class Result(val items: List<Hero>?):UiState()
        class Error(val description:String):UiState()

    }
}