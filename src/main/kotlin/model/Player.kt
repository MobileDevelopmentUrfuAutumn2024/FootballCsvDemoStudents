package model

data class Player(
    val name: String,
    val team: Team,
    val position: Position,
    val nationality: String,
    val agency: String?,
    val transferCost: Long,
    val participantsCount: Int,
    val goals: Int,
    val assists: Int,
    val yellowCards: Int,
    var redCards: Int
)

enum class Position(val russianName: String) {
    FORWARD("Нападающий"),
    MIDFIELD("Полузащитник"),
    DEFENDER("Защитник"),
    GOALKEEPER("Вратарь")
}
