package com.example.pokedex.models

data class PokemonDetail(
    val name: String,
    val sprites: String,
    val species: String,
    val types: List<String>?
)