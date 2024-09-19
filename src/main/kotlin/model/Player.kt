package model

data class Player(
    val name: String,
    val team: Team,
    val position: Position,
    val nationality: String,
    val agency: String?,
    val transferCost: String,
    val participation: Int,
    val goals: Int,
    val assists: Int,
    val yellowCards: Int,
    val redCards: Int)
