package com.example.domain.model

data class Command(
    val name: String,
    val player1: Player,
    val player2: Player,
    val score: Int = 0
)