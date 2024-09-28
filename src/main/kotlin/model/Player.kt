package model

class Player(
    val name: String,
    val team: Team,
    val city: String,
    val position: Position,
    val nationality: String,
    val agent: String,
    val price: Double,
    val countMatch: Int,
    val countGoal: Int,
    val countGoalBroadCast: Int,
    val countYellowCard: Int,
    val countRedCard: Int
) {
    enum class Position(val russianName: String) {
        MIDFIELD("Полузащитник"),
        DEFENDER("Защитник"),
        GOALKEEPER("Вратарь"),
        FORWARD("Нападающий")
    }
}



