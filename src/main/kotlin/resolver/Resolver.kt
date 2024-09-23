package resolver
import model.Player
import model.Team
import model.Position

class Resolver(private val players: List<Player>):IResolver {

    override fun getCountWithoutAgency(): Int {
        return players.count { it.agency == null || it.agency.isBlank() }
    }

    override fun getBestScorerDefender(): Pair<String, Int> {
        val bestDefender = players
            .filter { it.position == Position.DEFENDER }
            .maxByOrNull { it.goalsCount }

        return bestDefender?.let { it.name to it.goalsCount } ?: ("" to 0)
    }

    override fun getTheExpensiveGermanPlayerPosition(): String {
        val mostExpensiveGermanPlayer = players
            .filter { it.nationality == "Germany" }
            .maxByOrNull { it.transferCost }

        return when (mostExpensiveGermanPlayer?.position) {
            Position.GOALKEEPER -> "Вратарь"
            Position.DEFENDER -> "Защитник"
            Position.MIDFIELD -> "Полузащитник"
            Position.FORWARD -> "Нападающий"
            else -> "Неизвестно"
        }
    }

    override fun getTheRudestTeam(): Team {
        return players.groupBy { it.team }
            .maxByOrNull { (_, teamPlayers) ->
                teamPlayers.sumOf { it.redCardsCount }.toDouble() / teamPlayers.size
            }?.key ?: players.first().team
    }

}