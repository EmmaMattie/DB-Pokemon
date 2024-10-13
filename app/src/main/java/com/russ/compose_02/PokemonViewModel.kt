package com.russ.compose_02
import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PokemonViewModel(application: Application) : AndroidViewModel(application) {

    private val dbHelper = DBClass(application)
    var pokemonList = mutableStateListOf<PokemonDataClass>()
        private set

    var isLoading by mutableStateOf(true) // This should work correctly
        private set

    fun loadPokemons() {
        isLoading = true

        viewModelScope.launch(Dispatchers.IO) {

            delay(3000)
            pokemonList.clear()
            pokemonList.addAll(dbHelper.getAllPokemons())

            isLoading = false
        }
    }

    fun increaseAccessCount(pokemonDataClass: PokemonDataClass) {
        viewModelScope.launch(Dispatchers.IO) {

            dbHelper.increaseAccessCount(pokemonDataClass.id)
            loadPokemons()
        }
    }
}