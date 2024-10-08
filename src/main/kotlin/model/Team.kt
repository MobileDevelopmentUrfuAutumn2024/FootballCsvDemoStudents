package model

data class Team(
    val name: String,
    val city: String,
    val players: MutableList<Player>
)
