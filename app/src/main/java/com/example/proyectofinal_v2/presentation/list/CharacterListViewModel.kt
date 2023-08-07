package com.example.proyectofinal_v2.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinal_v2.domain.model.CharacterModel
import com.example.proyectofinal_v2.domain.usecase.GetCharacterListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterListViewModel (
    private val getCharacterListUseCase: GetCharacterListUseCase
        ): ViewModel(){
            private val _characterList = MutableLiveData<List<CharacterModel>>()
            val characterList : LiveData<List<CharacterModel>> get() = _characterList

            private val _errorMessage = MutableLiveData<String?>()
            val errorMessage : LiveData<String?> get() = _errorMessage

    init{
        getData()
    }

    private fun getData(){
        viewModelScope.launch {
            try{
                _errorMessage.value= null
                val result = withContext(Dispatchers.IO){
                    getCharacterListUseCase.invoke()
                }

                _characterList.value= result
            }catch (t: Throwable){
                _errorMessage.value = t.toString()
            }
        }
    }
}