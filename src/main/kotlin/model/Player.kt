package model

data class Player(
    val name: String,
    val team: Team,
    val position: Position,
    val nationality: String,
    val agency: String,
    val transferCost: Int,
    val participationCount: Int,
    val goalsCount: Int,
    val assistsCount: Int,
    val yellowCardsCount: Int,
    val redCardsCount: Int
)
