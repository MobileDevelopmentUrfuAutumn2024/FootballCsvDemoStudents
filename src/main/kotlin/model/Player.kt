package model

data class Player(
    val name: String,
    val team: Team,
    val position: Position,
    val nationality: String,
    val agency: String,
    val transferCost: Double,
    val participations: Int,
    val goals: Int,
    val assists: Int,
    val yellowCards: Int,
    val redCards: Int,
) {

    enum class Position { FORWARD, MIDFIELD, DEFENDER, GOALKEEPER }
}
