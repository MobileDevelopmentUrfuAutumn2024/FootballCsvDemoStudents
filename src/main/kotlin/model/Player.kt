package model

enum class PlayerPosition(val strName: String) {
    FORWARD("FORWARD"),
    GOALKEEPER("GOALKEEPER"),
    MIDFIELD("MIDFIELD"),
    DEFENDER("DEFENDER"),
}

data class PersonData(
    val name: String,
    val nationality: String,
    val position: PlayerPosition
)

data class TransferData(
    val agency: String,
    val cost: Int
)

data class StatisticsData(
    val participations: Int,
    val goals: Int,
    val assists: Int,
    val yellowCards: Int,
    val redCards: Int
)

class Player(
    val personData: PersonData,
    val transferData: TransferData,
    val statisticsData: StatisticsData
) {
}
