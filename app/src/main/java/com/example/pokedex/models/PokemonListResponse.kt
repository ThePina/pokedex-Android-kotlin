package com.example.pokedex.models

data class PokemonListResponse(
    val count: Int,
    val results: List<PokemonListItemResponse>
)
