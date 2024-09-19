package resolver

import model.Player
import model.Team

object Resolver: IResolver{

    private val positionsEnglishToRussian = mapOf(
        "MIDFIELD" to "Полузащитник",
        "DEFENDER" to "Защитник",
        "GOALKEEPER" to "Вратарь",
        "FORWARD" to "Нападающий"
    )

    override fun getCountWithoutAgency(players: List<Player>): Int {
        return players.count { it.agency.isEmpty() }
    }

    override fun getBestScorerDefender(players: List<Player>): Pair<String, Int> {
        return players.filter { it.position == "DEFENDER" }
            .maxBy { it.goals }
            .let { it.name to it.goals }
    }

    override fun getTheExpensiveGermanPlayerPosition(players: List<Player>): String {
        return players.filter { it.nationality == "Germany" }
            .maxBy { it.transferCost }
            .let { positionsEnglishToRussian.getOrDefault(it.position.capitalize(), it.position) }
    }

    override fun getTheRudestTeam(players: List<Player>): Team {
        return players.groupBy { it.team }
        .map { it -> it.key to it.value.sumOf { it.redCards } / it.value.count() }
        .maxBy { it.second }.first
    }

}