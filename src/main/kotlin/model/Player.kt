package model

data class Player(
    val name: String,
    val team: String,
    val city: String,
    val position: String,
    val nationality: String,
    val agency: String?,
    val transferCost: Int,
    val participation: Int,
    val goals: Int,
    val assists: Int,
    val yellowCards: Int,
    val redCards: Int
)