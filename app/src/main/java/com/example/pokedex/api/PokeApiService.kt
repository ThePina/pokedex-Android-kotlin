package com.example.pokedex.api


import com.example.pokedex.models.PokemonListResponse
import retrofit2.Call
import retrofit2.http.GET


interface PokeApiService {
    @GET("pokemon?limit=1292")
    fun getPokemonList(): Call<PokemonListResponse>

}