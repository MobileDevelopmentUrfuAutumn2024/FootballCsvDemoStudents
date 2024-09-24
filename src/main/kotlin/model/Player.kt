package model

class Player(
    val name: String,
    val team: Team,
    val position: Position,
    val nationality: String,
    val agency: String?,
    val transferCost: Long,
    val participation: Int,
    val goals: Int,
    val assists: Int,
    val yellowCardsCount: Int,
    val redCardsCount: Int,
)
