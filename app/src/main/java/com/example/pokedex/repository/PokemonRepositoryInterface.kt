package com.example.pokedex.repository

import com.example.pokedex.models.PokemonListItemResponse

interface PokemonRepositoryInterface {

    suspend fun getPokemonList(): List<PokemonListItemResponse>
}