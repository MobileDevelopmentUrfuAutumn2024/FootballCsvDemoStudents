package model

class Player(
    val name: String,
    val teamName: String,
    val city: String,
    val position: Position,
    val nationality: String,
    val agency: String?,
    val transferCost: Long,
    val participations: Int,
    val goals: Int,
    val assists: Int,
    val yellowCards: Int,
    val redCards: Int,
)

enum class Position(val localization: String) {
    DEFENDER("Защитник"),
    GOALKEEPER("Вратарь"),
    MIDFIELD("Полузащитник"),
    FORWARD("Нападающий")
}
