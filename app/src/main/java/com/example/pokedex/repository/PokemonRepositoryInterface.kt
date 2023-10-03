package com.example.pokedex.repository

import com.example.pokedex.models.PokemonDetail
import com.example.pokedex.models.PokemonDetailResponse
import com.example.pokedex.models.PokemonListItemResponse

interface PokemonRepositoryInterface {

    suspend fun getPokemonList(): List<PokemonListItemResponse>
    suspend fun getPokemonDetails(pokemonName: String): PokemonDetail
}