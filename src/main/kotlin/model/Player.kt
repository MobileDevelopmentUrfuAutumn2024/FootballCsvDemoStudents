package model

enum class FootballPosition(val s: String) {
    DEFENDER("DEFENDER"),
    FORWARD("FORWARD"),
    MIDFIELD("MIDFIELD"),
    GOALKEEPER("GOALKEEPER");

    fun toRussian(): String {
        return when (s) {
            "DEFENDER" -> "Защитник"
            "FORWARD" -> "Нападающий"
            "MIDFIELD" -> "Полузащитник"
            "GOALKEEPER" -> "Вратарь"
            else -> throw IllegalArgumentException("Unknown position: $s")
        }
    }
}

data class PlayerStats (
    val Participations: Int,
    val Goals: Int,
    val Assists: Int,
    val YellowCards: Int,
    var RedCards: Int
)

data class Player(
    val Name: String,
    val Team: Team,
    val Position: FootballPosition,
    val Nationality: String,
    val Agency: String?,
    val TransferCost: Long,
    val Stats: PlayerStats
)
