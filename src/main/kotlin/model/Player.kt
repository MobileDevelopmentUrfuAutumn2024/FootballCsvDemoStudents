package model

enum class EPosition(val russianName: String) {
    DEFENDER("Защитник"),
    FORWARD("Нападающий"),
    MIDFIELD("Полузащитник"),
    GOALKEEPER("Вратарь");
}

data class Player(
    val name: String,
    val team: Team,
    val position: EPosition?,
    val nationality: String,
    val agency: String?,
    val transferCost: Int?,
    val participation: Int?,
    val goals: Int?,
    val assists: Int?,
    val yellowCards: Int?,
    val redCards: Int?
)