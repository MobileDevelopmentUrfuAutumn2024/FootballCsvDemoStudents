package model

class Player (
    val name: String,
    val team: Team,
    val position: Position,
    val agency: String?,
    val transferСost: Long,
    val nationality: String,
    val participations: Int,
    val goals: Int,
    val assists: Int,
    val yellowCardCount: Int,
    val redCardsCount: Int,
)
