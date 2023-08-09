package com.example.proyectofinal_v2.presentation.favorites

import android.content.ClipData
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyectofinal_v2.domain.model.CharacterModel
import com.example.proyectofinal_v2.domain.usecase.GetCharacterListUseCase
import com.example.proyectofinal_v2.domain.usecase.GetFavoritesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoritesViewModel(
    private val getFavoritesUseCase: GetFavoritesUseCase
) : ViewModel() {
    private val _characterList = MutableLiveData<List<CharacterModel>>()
    val characterList: LiveData<List<CharacterModel>> get() = _characterList

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> get() = _errorMessage

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            try {
                _errorMessage.value = null
                val result = withContext(Dispatchers.IO) {
                    getFavoritesUseCase.invoke(favorite = true)
                }

                _characterList.value = result
            } catch (t: Throwable) {
                _errorMessage.value = t.toString()
            }
        }
    }

}