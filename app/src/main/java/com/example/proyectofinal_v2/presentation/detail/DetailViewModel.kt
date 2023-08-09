package com.example.proyectofinal_v2.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinal_v2.domain.model.CharacterModel
import com.example.proyectofinal_v2.domain.usecase.GetDetailUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel(
    private val getDetailUseCase: GetDetailUseCase
) : ViewModel() {

    private val _character = MutableLiveData<CharacterModel>()
    val character : LiveData<CharacterModel> get() = _character

    private val _errorMessage = MutableLiveData <String?>()
    val errorMessage : LiveData <String?> get() = _errorMessage

    fun getCharacter(id: Int) = viewModelScope.launch {
        try{
            val result = withContext(Dispatchers.IO){
                getDetailUseCase.invoke(id)
            }
            _character.value= result
        }catch (t:Throwable){
            _errorMessage.value = t.toString()
        }
    }
}