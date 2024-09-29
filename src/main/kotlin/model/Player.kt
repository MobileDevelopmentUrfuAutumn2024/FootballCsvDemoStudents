package model

class Player(
    val name: String,
    val team: Team,
    val position: Position,
    val agency: String?,
    val nationality: String,
    val transferCost: Long,
    val participations: Int,
    val goals: Int,
    val assists: Int,
    val yellowCards: Int,
    val redCards: Int
) {

    enum class Position(val russianName: String) {
        MIDFIELD("Полузащитник"),
        DEFENDER("Защитник"),
        GOALKEEPER("Вратарь"),
        FORWARD("Нападающий")
    }
}
