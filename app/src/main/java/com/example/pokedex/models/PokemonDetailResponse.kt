package com.example.pokedex.models

import com.google.gson.annotations.SerializedName

data class PokemonDetailResponse(
    val name: String,
    val sprites: Sprites,
    val species: Species,
    val types: List<Type>

)
data class Sprites(
    @SerializedName("front_default") val frontDefault: String
)

data class Species(
    val url: String
)

data class TypeDetail(
    val name: String,
    val url: String
)

data class Type(
    val slot: Int,
    val type: TypeDetail
)
