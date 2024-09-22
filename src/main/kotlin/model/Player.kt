package model

data class Player(
    val name: String,
    val team: Team,
    val position: PositionsRu,
    val nationality: String,
    val agency: String,
    val transferCost: Int,
    val participations: Int,
    val goals: Int,
    val assists: Int,
    val yellowCards: Int,
    val redCards: Int
)

enum class PositionsRu(val posRu: String){
    DEFENDER("Защитник"),
    FORWARD("Нападающий"),
    MIDFIELD("Полузащитник"),
    GOALKEEPER("Вратарь")
}