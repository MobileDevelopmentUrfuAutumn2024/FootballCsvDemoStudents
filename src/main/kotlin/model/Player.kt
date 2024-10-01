package model

enum class FootballPos(val russianName:String){
    DEFENDER("Защитник"),
    FORWARD("Нападающий"),
    MIDFIELD("Полузащитник"),
    GOALKEEPER("Вратарь");
}

data class Player(
    val name: String,
    val team: Team,
    val position: FootballPos,
    val nation: String,
    val agency: String?,
    val transferCost: Int,
    val playerStat: PlayerStatistics
)

data class PlayerStatistics(
    val matchesCount: Int,
    val goals: Int,
    val assists: Int,
    val yellowCardsCount: Int,
    val redCardsCount: Int
)
