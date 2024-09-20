package resolver

import model.Player
import model.Team
import model.enums.Position

class Resolver(val players: Array<Player>) : IResolver {

    init {
        if (players.isEmpty())
            throw Exception("Players list must have at least one element")
    }

    override fun getCountWithoutAgency(): Int {
        return players.count { it.agency.isEmpty() }
    }

    override fun getBestScorerDefender(): Pair<String, Int> {
        return players
            .filter { it.position == Position.DEFENDER }
            .maxByOrNull { it.goals }
            ?.let { it.name to it.goals } ?: Pair("Неопределён", 0)
    }

    override fun getTheExpensiveGermanPlayerPosition(): String {
        val position = players
            .filter { it.nationality == "Germany" }
            .maxByOrNull { it.transferCost }
            ?.position
        return when (position) {
            Position.GOALKEEPER -> "Вратарь"
            Position.DEFENDER -> "Защитник"
            Position.MIDFIELD -> "Полузащитник"
            Position.FORWARD -> "Нападющий"
            else -> "Неопределеный"
        }
    }

    override fun getTheRudestTeam(): Team {
        return players
            .groupBy { it.team }
            .maxBy { it.value.map { p -> p.redCards }.average() }
            .key
    }

    fun getStatistic(): List<Pair<Team, Int>> {
        return players
            .groupBy { it.team }
            .map { it.key to it.value.map { p -> p.transferCost }.sum() }
            .sortedBy { it.second }
            .take(10)
    }
}