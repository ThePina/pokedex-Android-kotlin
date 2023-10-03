package com.example.pokedex.api


import com.example.pokedex.models.PokemonDetailResponse
import com.example.pokedex.models.PokemonItemList
import com.example.pokedex.models.PokemonListResponse

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url


interface PokeApiService {
    @GET("pokemon?limit=1292")
    fun getPokemonList(): Call<PokemonListResponse>

    @GET("pokemon/{pokemonName}")
    fun getPokemonByName(@Path("pokemonName") pokemonName: String): Call<PokemonDetailResponse>



}