package com.russ.compose_02

data class PokemonDataClass(
    val id: Int = 0,
    val name: String,
    val num: Int,
    val powerLevel: Int,
    val description: String,
    var accessCount: Int = 0,
    val image: String,
)
