package com.example.pokedex.repository

import com.example.pokedex.api.PokeApiService
import com.example.pokedex.models.PokemonItemList
import com.example.pokedex.models.PokemonListItemResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonRepository(): PokemonRepositoryInterface {
    private val api: PokeApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(PokeApiService::class.java)
    }



    override suspend fun getPokemonList(): List<PokemonListItemResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getPokemonList().execute()
                if (response.isSuccessful) {
                    val pokemonResponse = response.body()

                    pokemonResponse?.results ?: emptyList()
                } else {
                    throw Exception("Error")
                }
            } catch (e: Exception) {
                throw e
            }
        }
    }
}