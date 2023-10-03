package com.example.pokedex.repository

import com.example.pokedex.models.PokemonDetail
import com.example.pokedex.models.PokemonDetailResponse
import com.example.pokedex.models.PokemonListItemResponse

interface PokemonRepositoryInterface {
    //obtener la lista de todos los pokemon
    suspend fun getPokemonList(): List<PokemonListItemResponse>

    //obtener detalles especificados el modelo de PokemonDetail
    suspend fun getPokemonDetails(pokemonName: String): PokemonDetail
}